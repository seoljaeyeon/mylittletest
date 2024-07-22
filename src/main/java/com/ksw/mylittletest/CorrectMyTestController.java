package com.ksw.mylittletest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ksw.service.forObject.entity.CategoryService;
import com.ksw.service.forObject.relation.NoteCategoryService;
import com.ksw.service.function.AuthService;
import com.ksw.service.function.QuestionService;
import com.ksw.vo.forObject.entity.UserVO;
import com.ksw.vo.function.QuestionVO;

@Controller
@RequestMapping("/correctmytest")
public class CorrectMyTestController {

	@Autowired
	private AuthService authService;
	@Autowired
	private NoteCategoryService noteCategoryService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public String toCategory(
			RedirectAttributes redirectAttribute) {
			Integer menuType = 1;
			redirectAttribute.addFlashAttribute("menuType", menuType);
		return "redirect:/correctmytest/category";
	}
	
	@GetMapping("/category")
	public String viewCategoryPage(
			RedirectAttributes redirectAttributes,
            @RequestParam(value="page", required = false, defaultValue="1") Integer page,
            Model model
			){
	    
	    Integer menuType = (Integer) model.asMap().get("menuType");
	    String  menuName = "correctmytest";
	    // menuType이 null인 경우 처리
	    if (menuType == null) {
	        menuType = 2;
	        redirectAttributes.addFlashAttribute("menuType", menuType);
	        return "redirect:/category";
	    }

		UserVO userVO = authService.getUserVO();
		if (userVO == null) {
			return "redirect:/login";
		}
		
		List<List<Map<String, Object>>> list = new ArrayList<>();
		list = categoryService.getListByViewOrder(userVO.getUserNo(), menuType, page);
	    model.addAttribute("list", list);
	    model.addAttribute("menuName", menuName);
		return "questionlist";
	}
	
	@GetMapping("/category/{categoryTitle}")
	public String getCorrectRandom(
			@PathVariable("categoryTitle") String categoryTitle, 
			HttpSession session,
			Model model) {
		
        Optional<UserVO> auth = Optional.ofNullable(authService.getUserVO());
        if (!auth.isPresent()) {
            return "redirect:/login";
        }
		
		UserVO userVO = auth.get();
		Integer menuType = 2;
		
		// 사용자 정보 저장
		model.addAttribute("userVO", userVO);
		
		// 사용자가 푼 문제 중, 사용자가 맞춘 문제 중에서 랜덤문제 출제
		Integer random = noteCategoryService.getRandomNobyCategoryTitle(categoryTitle, userVO.getUserNo(), menuType);
		if (random == null || random == 0) {
			// 추가적인 처리 또는 오류 페이지로 리다이렉트
			return "redirect:/correctmytest/category";
		}		
		
		return "redirect:/correctmytest/category/"+categoryTitle+"/"+random;
	}
	
	@GetMapping("/category/{categoryTitle}/{noteNo}")
	@Transactional
	public String viewPage(
			@PathVariable("noteNo") Integer noteNo,
			@PathVariable("categoryTitle") String categoryTitle,
			Model model,
			HttpServletRequest request,
			HttpSession session) { 
		
        Optional<UserVO> auth = Optional.ofNullable(authService.getUserVO());
        if (!auth.isPresent()) {
            return "redirect:/login";
        }
		
		UserVO userVO = auth.get();
		String menuName = "correctmytest";
		
		// 사용자 정보 저장
		model.addAttribute("userVO", userVO);
				
		// DB에서 문제 정보 가져오기 
		QuestionVO questionVO = questionService.Read(noteNo, userVO, request, session);

		// 모델에 문제 정보 세팅
		model.addAttribute("questionVO", questionVO);
		model.addAttribute("menuName", menuName);
		
		return "questionsolve"; 
	}
	
}
