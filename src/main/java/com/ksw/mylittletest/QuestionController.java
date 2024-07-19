package com.ksw.mylittletest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ksw.dto.forObject.entity.CategoryDTO;
import com.ksw.dto.forObject.entity.NoteDTO;
import com.ksw.service.forObject.entity.NoteService;
import com.ksw.service.forObject.entity.UserService;
import com.ksw.service.forObject.relation.NoteCategoryService;
import com.ksw.service.forObject.relation.NoteViewService;
import com.ksw.service.function.AuthService;
import com.ksw.service.function.QuestionService;
import com.ksw.vo.forObject.entity.UserVO;
import com.ksw.vo.function.QuestionVO;

@Controller
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	@Autowired
	private NoteService noteService;
	@Autowired
	private AuthService authService;
	@Autowired
	private UserService userService;
	@Autowired
	private NoteViewService noteViewService;
	@Autowired
	private NoteCategoryService noteCategoryService;
	
	@GetMapping("/write")
	public String toWritePage(
			Model model
			) {
		UserVO userVO = authService.getUserVO();
		
		// 사용자 정보 저장
		model.addAttribute("userVO", userVO);
		return "write";
	}
	
	@GetMapping("/myTest")
	public String toCategory() {
		return "redirect:/myTest/category";
	}
	
	@GetMapping("/myTest/category")
	public String viewCategoryPage()
	{
		return "questionlist";
	}
	@GetMapping("/myTest/category/{categoryTitle}")
	public String getRandom(
			@PathVariable("categoryTitle") String categoryTitle, 
			HttpSession session,
			Model model) {
		
        Optional<UserVO> auth = Optional.ofNullable(authService.getUserVO());
        if (!auth.isPresent()) {
            return "redirect:/login";
        }
		
		UserVO userVO = auth.get();
		String menuName = "내 문제 풀기";
		
		// 사용자 정보 저장
		model.addAttribute("userVO", userVO);
		
		// 사용자가 작성한 문제 중, 사용자가 보지 못한 문제 중에서 랜덤문제 출제
		// 보지 못한 문제가 없다면 본 문제 중에서 랜덤으로 출제
		Integer random = noteCategoryService.getRandomNobyCategoryTitle(categoryTitle, userVO.getUserNo());
		if (random == null || random == 0) {
			// 추가적인 처리 또는 오류 페이지로 리다이렉트
			return "redirect:/myTest/category";
		}		
		
		return "redirect:/myTest/category/"+categoryTitle+"/"+random;
	}
	
	// 맞춘문제
	@GetMapping("/correctmytest")
	public String CorrectToCategory() {
		return "redirect:/correctmytest/category";
	}
	
	@GetMapping("/correctmytest/category")
	public String CorrectToCategoryPageView()
	{
		
		return "questionlist";
	}
	@GetMapping("/correctmytest/category/{categoryTitle}")
	public String getCorrectRandom(
	        @PathVariable("categoryTitle") String categoryTitle, 
	        HttpSession session,
	        Model model) {

		 UserVO userVO = authService.getUserVO();
		    if (userVO == null) {
		        return "redirect:/login";
		    }

		    System.out.println("Authenticated user: " + userVO);
		    System.out.println("Requested categoryTitle: " + categoryTitle);

		    Integer random = noteCategoryService.getRandomNobyCorrectCategoryTitle(categoryTitle, userVO.getUserNo());
		    System.out.println("Random note number: " + random);

		    if (random == null || random == 0) {
		    	System.out.println("푼 적이 없습니다");
		        // 쿼리 파라미터로 메시지를 전달
		        return "redirect:/correctmytest/category?message=NoSolvedQuestions";
		    }

		    return "redirect:/correctmytest/category/" + categoryTitle + "/" + random;
	}
	
 	
	@GetMapping("/myTest/category/{categoryTitle}/{noteNo}")
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
		String menuName = "내 문제 풀기";
		
		// 사용자 정보 저장
		model.addAttribute("userVO", userVO);
				
		// DB에서 문제 정보 가져오기 
		QuestionVO questionVO = questionService.Read(noteNo, userVO, request, session);

		// 모델에 문제 정보 세팅
		model.addAttribute("questionVO", questionVO);
		model.addAttribute("menuName", menuName);
		
		return "questionsolve"; 
	}
	
	@GetMapping("/correctmytest/category/{categoryTitle}/{noteNo}")
	@Transactional
	public String correctviewPage(
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
		String menuName = "맞춘 문제 풀기";
		
		// 사용자 정보 저장
		model.addAttribute("userVO", userVO);
				
		// DB에서 문제 정보 가져오기 
		QuestionVO questionVO = questionService.Read(noteNo, userVO, request, session);

		// 모델에 문제 정보 세팅
		model.addAttribute("questionVO", questionVO);
		model.addAttribute("menuName", menuName);
		
		return "questionsolve"; 
	}
	
    @PostMapping(value = "/write", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public Map<String, String> notewrite(
			@ModelAttribute NoteDTO noteDTO,
			@ModelAttribute CategoryDTO categoryDTO,
			@RequestParam("file") MultipartFile file,
			HttpSession session,
			RedirectAttributes redirectAttributes,
	        HttpServletRequest request,
	        Model model) {
			// 인증 서비스로부터 사용자 정보 로딩
			UserVO userVO = authService.getUserVO();
			
			// 응답용 Map생성 (Json)
			Map<String, String> response = new HashMap<String, String>();
			if (userVO == null || userVO.getUserNo() == null) {
				response.put("status", "loing_needed");
				response.put("url", "/mylittletest/write");
				return response;
			}
				
			// 사용자 정보model에 추가
			model.addAttribute("userVO", userVO);
            try {
            	QuestionVO questionVO = questionService.Write(noteDTO, file, categoryDTO, userVO);
            	model.addAttribute("questionVO", questionVO); // view에서 어떻게 쓸 지 아직 미정
            	model.addAttribute("fromWrite", "true");
                response.put("status", "success");
                response.put("url", "/mylittletest/myTest/category/" + questionVO.getCategoryVO().getCategoryTitle() + "/" + questionVO.getNoteVO().getNoteNo());
                return response;
                
            } catch (Exception e) {
                response.put("status", "fail");
                response.put("url", "/mylittletest/write");
            	return response;
            }	
	}
}
