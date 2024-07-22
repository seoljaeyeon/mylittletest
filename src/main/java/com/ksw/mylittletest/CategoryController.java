package com.ksw.mylittletest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@GetMapping(value = "/allcategory")
	public String toAllCategory(
			Model model,
			RedirectAttributes redirectAttributes,
            @RequestParam(value="page", required = false, defaultValue="1") Integer page
			){

		UserVO userVO = authService.getUserVO();
		if (userVO == null) {
			return "redirect:/login";
		}
		
		Integer menuType = 0;
		
		List<List<Map<String, Object>>> list = new ArrayList<>();
		list = categoryService.getListByViewOrder(userVO.getUserNo(), menuType, page);
		model.addAttribute("list", list);
		return "questionlist";
	}
	
	
	@GetMapping
	public String categoryMain(
			Model model,
	        @RequestParam(value = "page", defaultValue = "1") Integer page, 
	        RedirectAttributes redirectAttributes
			) {
		UserVO userVO = authService.getUserVO();
		String menuPath = "";

	    Integer menuType = (Integer) model.asMap().get("menuType");

		
		if (userVO == null) {
			return "redirect:/login";
		}
		
		if (menuType == null || menuType == 0) {
			return "redirect:/category/allcategory";
		}
		
        if (menuType != null) {
            if (menuType == 1) {
                menuPath = "mytest";
            } else if (menuType == 2) {
                menuPath = "correctmytest";
            } else if (menuType == 3) {
                menuPath = "reviewmytest";
            } else if (menuType == 4) {
                menuPath = "todayquestions";
            } else if (menuType == 5) {
                menuPath = "bookmarkquestions";
            }
            redirectAttributes.addFlashAttribute("menuType", menuType);
        }
		
		return "redirect:/"+menuPath+"/category";
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