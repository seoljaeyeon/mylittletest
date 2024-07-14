package com.ksw.mylittletest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ksw.service.forObject.relation.NoteViewService;
import com.ksw.service.function.AuthService;
import com.ksw.service.function.QuestionService;
import com.ksw.vo.forObject.entity.UserVO;
import com.ksw.vo.forObject.relation.NoteViewVO;
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
	
	@GetMapping("/write")
	public String toWritePage(
			Model model
			) {
		UserVO userVO = authService.getUserVO();
		
		// 사용자 정보 저장
		model.addAttribute("userVO", userVO);
		return "write";
	}

	@GetMapping("/view/{noteNo}")
	@Transactional
	public String viewPage(
			@PathVariable("noteNo") Integer noteNo,
			Model model) { 
		/*
		 * 필요 기능 목록
		 * - 조회 수 증가 시키기 (조회 이력 남기기)
		 * - 댓글 목록 로딩 --> questionService.Read에서 처리
		 * - 댓글 쓰기 기능(ReplyController에서 처리)
		 * - 수정 관련 컨트롤러 만들기
		 * - 비활성화 컨트롤러 만들기
		 * - 덜보기 컨트롤러 만들기
		 * - 오늘 조회 목록 불러오기
		 * - 다음 문제 보기
		 */
		UserVO userVO = authService.getUserVO();

		// 사용자 정보 저장
		model.addAttribute("userVO", userVO);
				
		// DB에서 문제 정보 가져오기 
		QuestionVO questionVO = questionService.Read(noteNo, userVO);

		// 모델에 문제 정보 세팅
		model.addAttribute("questionVO", questionVO);
		
		return "questionsolve"; 
	}
	
//    @GetMapping("/randomView")
//    public String RandomViewPage(
//            RedirectAttributes redirectAttributes,
//            Model model,
//            HttpSession session) {
//    	// questionVO를 model에서 가져옴
//    	UserVO userVO = authService.getUserVO();
//    	
//    	// userVO가 null일 경우 -> 로그인 X
//        if (userVO == null) {
//            redirectAttributes.addFlashAttribute("error", "로그인 필요");
//            return "redirect:/login"; // 로그인 페이지로 리디렉션
//        }
//        
//        // questionVO가 null일 경우 -> 랜덤진입
//        if (questionVO == null) {
//        	// 
//			Note randomNote = noteService.getRandomUnviewedNoteByCategory(
//					questionVO.getCategoryVO().getCategoryNo(), 
//					userVO.getUserNo());
//			
//			noteNo = randomNote.getNoteNo();
//			
//			questionVO = questionService.Read(noteNo, userVO.getUserNo());
//			
//			session.setAttribute("questionVO", questionVO);
//			
//			List<ViewHistoryVO> viewHistory = viewHistoryService.getHistoryByCategory(
//					questionVO.getCategoryVO().getCategoryNo(), 
//					userVO.getUserNo());
//        }
//        
//        if (questionVO != null) {
//
//        		if(' == null) {
//        		}
//        		QuestionVO newQuestionVO = questionService.Read(noteNo, userNo);
//        		session.setAttribute("questionVO", newQuestionVO); // 문제 정보
//        		session.setAttribute("viewHistory", viewHistory); // 해당 카테고리 내의 문제 중 사용자가 본 문제 조회이력
//
//        	return "view";
//        } else {
//        	
//        }
//    }
    
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
                response.put("status", "success");
                response.put("url", "/mylittletest/view/" + questionVO.getNoteVO().getNoteNo());
                return response;
                
            } catch (Exception e) {
                response.put("status", "fail");
                response.put("url", "/mylittletest/write");
            	return response;
            }	
	}
}
