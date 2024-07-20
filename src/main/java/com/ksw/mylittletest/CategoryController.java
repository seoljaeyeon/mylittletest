package com.ksw.mylittletest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ksw.service.forObject.entity.CategoryService;
import com.ksw.service.forObject.relation.AnswerHistoryService;
import com.ksw.service.forObject.relation.CategoryUserService;
import com.ksw.service.forObject.relation.CategoryViewService;
import com.ksw.service.forObject.relation.NoteCategoryService;
import com.ksw.service.function.AuthService;
import com.ksw.vo.forObject.entity.UserVO;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private AnswerHistoryService answerHistoryService;
	@Autowired
	private CategoryUserService categoryUserService;
	@Autowired
	private	NoteCategoryService noteCategoryService;
	@Autowired
	private	CategoryViewService categoryViewService;
	@Autowired
	private	CategoryService categoryService;
	@Autowired
	private AuthService authService;
	
	@GetMapping
	public String categoryMain(
			Model model,
			Integer categoryNo,
	        @RequestParam(value = "page", defaultValue = "1") Integer page
			) {
		UserVO userVO = authService.getUserVO();
		
		if ((Integer) model.getAttribute("menuType") == 1) {
			model.addAttribute("menuType", "내 문제 풀기");
		} else if ((Integer) model.getAttribute("menuType") == 2) {
			model.addAttribute("menuType", "틀린 문제 복습");
		}else if ((Integer) model.getAttribute("menuType") == 3) {
			model.addAttribute("menuType", "맞춘 문제 복습");
		}else if ((Integer) model.getAttribute("menuType") == 4) {
			model.addAttribute("menuType", "오늘 본 문제 복습");
		}else if ((Integer) model.getAttribute("menuType") == 5) {
			model.addAttribute("menuType", "북마크 문제 복습");
		}
		
		if (userVO == null) {
			return "redirect:/login";
		}
		
		List<Map<String, Object>> list = categoryService.getListByViewOrder(categoryNo, userVO.getUserNo(), page);
        model.addAttribute("categoryDetail", list);
        
//		글 목록
//		- 4개씩 보이게 해야함
//		- 카테고리 이름,카테고리번호 받아야함
//		- 정답률,출제자수,좋아요수,문제수 가져와야함 (정답률,출제자수는 꼭 필요한가? 뺴야될건 빼야할거같음)
		// 진행률?-> answerHistory? 출제자 수? -> categoryUser? 문제 수? -> NoteCategory
//		- 드랍다운에 클릭했을떄 보여주는 주소적어놓음 (DB구현필요)
		
		return "questionlist";
	}
	
	@PostMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public Map<String, Object> categoriesInSearchBar(
			@RequestParam("data") String data,
			HttpSession session
			){
		UserVO userVO = authService.getUserVO();
		Map<String, Object> response = new HashMap<>();
		if (userVO == null) {
			response.put("status", "login_needed");
			response.put("url", "/mylittletest/login");
			return response;
		}
		
		if (data == null) {
			response.put("status", "parameter_null");
			return response;
		}
		
	    // 이전 요청 시간이 있는지 확인
	    Long previousRequestTime = (Long) session.getAttribute("requestTime");
	    Long currentTime = System.currentTimeMillis();

	    if (previousRequestTime != null && (currentTime - previousRequestTime) < 500) {
	        response.put("status", "too_early");
	        return response;
	    }

	    // 현재 요청 시간을 세션에 저장
	    session.setAttribute("requestTime", currentTime);
	    try {
	    	List<Map<String, Object>> result = categoryService.search(data);
	    	response.put("status", "success");
	    	if (result.isEmpty()) {
	    		response.put("data", data+"| 게시물 없음");
	    	}
	    	response.put("data", result);
	    	
	    } catch (Exception e) {
	    	response.put("status", "failed");
	    	e.printStackTrace();
	    	System.out.println("문제있");
	    	return response;
	    }
		return response;
	}
}