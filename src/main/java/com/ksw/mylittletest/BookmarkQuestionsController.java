package com.ksw.mylittletest;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ksw.service.forObject.relation.NoteCategoryService;
import com.ksw.service.function.AuthService;
import com.ksw.service.function.QuestionService;
import com.ksw.vo.forObject.entity.UserVO;
import com.ksw.vo.function.QuestionVO;

@Controller
@RequestMapping("/bookmarkquestions")
public class BookmarkQuestionsController {

	@Autowired
	private AuthService authService;
	@Autowired
	private NoteCategoryService noteCategoryService;
	@Autowired
	private QuestionService questionService;

	@GetMapping
	public String toCategory() {
		return "redirect:/bookmarkquestions/category";
	}
	
	@GetMapping("/category")
	public String viewCategoryPage(
			Model model)
	{
		Integer menuType = 4;
		model.addAttribute("menuType", menuType);
		return "redirect:/category";
	}
	@GetMapping("/category/{categoryTitle}")
	public String getRandom(
			@PathVariable("categoryTitle") String categoryTitle, 
			HttpSession session,
			Model model) {
		
        Optional<UserVO> auth = Optional.ofNullable(authService.getUserVO());
        if (!auth.isPresent()) {
            return "redirect:/login";
        }
		
		UserVO userVO = auth.get();
		Integer menuType = 5;
		
		
		// 사용자 정보 저장
		model.addAttribute("userVO", userVO);
		
		// 사용자가 작성한 문제 중, 사용자가 보지 못한 문제 중에서 랜덤문제 출제
		// 보지 못한 문제가 없다면 본 문제 중에서 랜덤으로 출제
		Integer random = noteCategoryService.getRandomNobyCategoryTitle(categoryTitle, userVO.getUserNo(), menuType);
		if (random == null || random == 0) {
			// 추가적인 처리 또는 오류 페이지로 리다이렉트
			return "redirect:/bookmarkquestions/category";
		}		
		
		return "redirect:/bookmarkquestions/category/"+categoryTitle+"/"+random;
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
		String menuName = "오늘 본 문제 복습";
		
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
