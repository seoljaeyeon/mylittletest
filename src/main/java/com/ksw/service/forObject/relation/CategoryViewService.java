package com.ksw.service.forObject.relation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dao.forObject.relation.CategoryViewMapper;
import com.ksw.dto.forObject.relation.CategoryViewDTO;
import com.ksw.object.entity.View;
import com.ksw.object.relation.CategoryView;
import com.ksw.service.forObject.entity.CategoryService;
import com.ksw.service.forObject.entity.UserService;
import com.ksw.service.forObject.entity.ViewService;
import com.ksw.service.function.AuthService;
import com.ksw.service.function.ClientInfoService;
import com.ksw.vo.forObject.relation.CategoryViewVO;

@Service
public class CategoryViewService {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ViewService viewService;
	@Autowired
	private UserService userService;
	@Autowired
	private ClientInfoService clientInfoService;
	@Autowired
	private CategoryViewMapper categoryViewMapper;
	@Autowired
	private AuthService authService;
	
	public List<Map<String,Object>> getTodayCategoryView(Integer userNo, Integer menuType){
		
		List<Map<String,Object>> result = new ArrayList<>();

		switch(menuType) {
		case 1:
			result = categoryViewMapper.getTodayHistoryByUserNoOfMyTest(userNo);
			break;
		case 2:
			result = categoryViewMapper.getTodayHistoryByUserNoAndAnswerType(userNo, 2);
			break;
		case 3:
			result = categoryViewMapper.getTodayHistoryByUserNoAndAnswerType(userNo, 1);
			break;
		case 4:
			result = categoryViewMapper.getTodayHistoryByUserNo(userNo);
			break;
		case 5:
			result = categoryViewMapper.getTodayHistoryByUserNoAndFavoriteType(userNo, 1);
			break;
		}
		
		return result;
	}
	
	public void categoryViewIncrease(
			String categoryTitle,
			HttpServletRequest request,
			HttpSession session){
		
		Integer categoryNo = categoryService.getCategoryNoByTitle(categoryTitle);
		
		// 조회 이력 확인 및 등록
		if(!clientInfoService.getSessionClientCategoryViewHistory(categoryNo, session, request)) {
			// 세션에 조회 이력 등
			clientInfoService.saveClientInfoInSession(categoryNo, request, session);
			if (!this.checkRecentViewHistory(categoryNo, authService.getUserVO().getUserNo())) {
				System.out.println("최근 기록에도 없음");
				View view = new View(); // view Entity 객체 생성
				view = viewService.insert(view); // view DB에 기록
				HashMap<Integer, HashMap<String, String>> clientInfos = (HashMap<Integer, HashMap<String, String>>) session.getAttribute("clientInfos");
				HashMap<String,String> clientInfo = clientInfos.get(categoryNo);
				this.insertRelation(view.getViewNo(), categoryNo, authService.getUserVO().getUserNo());
				System.out.println("categoryView insert 성공");
			}
		};
	}
	
	public void insertRelation(Integer viewNo, Integer categoryNo, Integer userNo) {
		categoryViewMapper.insert(viewNo, categoryNo, userNo);
	}
	
	public Boolean checkRecentViewHistory(Integer categoryNo, Integer userNo) {
		if (categoryViewMapper.checkRecentViewHistory(categoryNo, userNo) == 0) {
			return false;
		}
		return true;
	}
	
	public CategoryViewDTO convertToDTO(CategoryView categoryView) {
		CategoryViewDTO dto = new CategoryViewDTO();
		if (categoryView == null) {
    		System.out.println("CategoryView to CategoryViewDTO failed. Empty CategoryViewDTO created. CategoryView is null");
			return dto;
		}
		dto.setCategoryDTO(categoryService.convertToDTO(categoryView.getCategory()));
		dto.setUserDTO(userService.convertToDTO(categoryView.getUser()));
		dto.setViewDTO(viewService.convertToDTO(categoryView.getView()));
		return dto; 
	}
	
	public CategoryViewVO convertToVO(CategoryViewDTO dto) {
		if (dto == null) {
			System.out.println("CategoryViewDTO to CategoryViewVO failed. Empty CategoryViewVO created. CategoryViewDTO is null");		
			return new CategoryViewVO.Builder().build();
		}
		return new CategoryViewVO.Builder()
				.categoryVO(categoryService.convertToVO(dto.getCategoryDTO()))
				.viewVO(viewService.convertToVO(dto.getViewDTO()))
				.userVO(userService.convertToVO(dto.getUserDTO()))
				.build();
	}
	
}
