package com.ksw.mylittletest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ksw.dto.forObject.entity.CategoryDTO;
import com.ksw.dto.forObject.entity.FileDTO;
import com.ksw.dto.forObject.entity.NoteDTO;
import com.ksw.dto.forObject.entity.UserDTO;
import com.ksw.object.entity.Note;
import com.ksw.service.forObject.entity.CategoryService;
import com.ksw.service.forObject.entity.FileService;
import com.ksw.service.forObject.entity.NoteService;
import com.ksw.service.forObject.entity.ReplyService;
import com.ksw.service.forObject.entity.UserService;
import com.ksw.service.function.AuthService;
import com.ksw.service.function.QuestionService;
import com.ksw.service.function.ViewHistoryService;
import com.ksw.vo.forObject.entity.CategoryVO;
import com.ksw.vo.forObject.entity.FileVO;
import com.ksw.vo.forObject.entity.NoteVO;
import com.ksw.vo.forObject.entity.UserVO;
import com.ksw.vo.function.QuestionVO;
import com.ksw.vo.function.ViewHistoryVO;

@Controller
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	@Autowired
	private ViewHistoryService viewHistoryService;
	@Autowired
	private NoteService noteService;
	@Autowired
	private AuthService authService;
	@Autowired
	private UserService userService;

	@GetMapping("/write")
	public String toWritePage(
			Model model) {
		UserVO userVO = authService.getUserVO();
		model.addAttribute("userVO", userVO);
		
		if (userVO == null) {
			return "login";
		}
		
		return "write";
	}

	@GetMapping("/view")
	public String viewPage(
			@RequestParam("no") Integer no,
			Model model
			) {
		UserVO certifiedReader = authService.getUserVO();
		
		if (certifiedReader == null) {
			return "/login";
		}
		
		// DB에서 문제 정보 가져오기
		QuestionVO questionVO = questionService.Read(no, certifiedReader.getUserNo());
		
		// 사용자가 해당 카테고리에서 본 문제 목록 가져오기 
		List<ViewHistoryVO> userHistory = viewHistoryService.getHistoryByCategory(certifiedReader.getUserNo(), questionVO.getCategoryVO().getCategoryNo());
		
		model.addAttribute("userVO", certifiedReader);
		model.addAttribute("questionVO", questionVO);
		model.addAttribute("viewHistory", userHistory);
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
    
	@PostMapping("/write")
	public String notewrite(
			@ModelAttribute NoteDTO noteDTO,
			@ModelAttribute CategoryDTO categoryDTO,
			@RequestParam("file") MultipartFile file,
			HttpSession session,
			RedirectAttributes redirectAttributes,
	        HttpServletRequest request,
	        Model model) {
		
			UserVO userVO = authService.getUserVO();
			model.addAttribute("userVO", userVO);
			UserDTO userDTO = userService.convertVOToDTO(userVO);
		
			System.out.println("1");
			System.out.println(userDTO.getUserNo());
            try {
            	QuestionVO questionVO = questionService.Write(noteDTO, file, categoryDTO,userDTO);
            	System.out.println("2");
            	System.out.println("성공했니");
                return "redirect:/view?no="+questionVO.getNoteVO().getNoteNo();
            } catch (Exception e) {
            	System.out.println("실패했니");
            	return "write";
            }	
	}
}
