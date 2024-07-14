package com.ksw.service.forObject.relation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dto.forObject.relation.CategoryViewDTO;
import com.ksw.object.relation.CategoryView;
import com.ksw.service.forObject.entity.CategoryService;
import com.ksw.service.forObject.entity.UserService;
import com.ksw.service.forObject.entity.ViewService;
import com.ksw.vo.forObject.relation.CategoryViewVO;

@Service
public class CategoryViewService {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ViewService viewService;
	@Autowired
	private UserService userService;
	
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
