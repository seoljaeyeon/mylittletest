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
@RequestMapping("/correctmytest")
public class CorrectMyTestController {

	@Autowired
	private AuthService authService;
	@Autowired
	private NoteCategoryService noteCategoryService;
	@Autowired
	private QuestionService questionService;
	
	// 맞춘문제
	@GetMapping
	public String Category() {
		return "redirect:/corretmytest/category";
	}
	
	@GetMapping("/category")
	public String viewCategoryPage(
			Model model)
	{
		Integer menuType = 2;
		model.addAttribute("menuType", menuType);
		return "redirect:/category";
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
			return "redirect:/corretmytest/category";
		}		
		
		return "redirect:/corretmytest/category/"+categoryTitle+"/"+random;
	}
	
	@GetMapping("/category/{categoryTitle}/{noteNo}")
	@Transactional
	public String viewPage(
			@PathVariable("noteNo") Integer noteNo,
			@PathVariable("categoryTitle") String categoryTitle,
			Model model,
			HttpServletRequest request,
			HttpSession session) { 
		/*
		 * 필요 기능 목록
		 * - 조회 수 증가 시키기 (조회 이력 남기기) [구현 - 완] [테스트 - 완]
		 * - 댓글 목록 로딩 --> questionService.Read에서 처리 [완] [테스트 - 완]
		 * - 댓글 쓰기 기능(ReplyController에서 처리) - 만들어야함 [구현 - 완] [테스트 - 완]
		 * - 오늘 조회 목록 불러오기 - [완] [테스트 - 완]
		 * - 다음 문제 보기 - 만들어야함 (randomview 수정마무리 완) [구현 - 완][테스트 - 완]
		 * - 메뉴 이름 출력 [완]
		 * - 수정 관련 컨트롤러 만들기  
		 * - 비활성화 컨트롤러 만들기
		 * - 덜보기 컨트롤러 만들기
         * - 문제 전체 보기 목록
         * - 신고 컨트롤러
         * - 좋아요 버튼
         * - 댓글 수정 
         * - 댓글 삭제
         * - 정답 입력  
         * - 공유하기 
		 */
		
        Optional<UserVO> auth = Optional.ofNullable(authService.getUserVO());
        if (!auth.isPresent()) {
            return "redirect:/login";
        }
		
		UserVO userVO = auth.get();
		String menuName = "틀린 문제 복습";
		
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
