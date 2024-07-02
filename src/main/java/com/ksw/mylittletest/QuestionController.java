package com.ksw.mylittletest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ksw.dto.forObject.object.CategoryDTO;
import com.ksw.dto.forObject.object.FileDTO;
import com.ksw.dto.forObject.object.NoteDTO;
import com.ksw.dto.forObject.object.UserDTO;
import com.ksw.object.vo.combined.QuestionVO;
import com.ksw.object.vo.object.CategoryVO;
import com.ksw.object.vo.object.FileVO;
import com.ksw.object.vo.object.NoteVO;
import com.ksw.object.vo.object.UserVO;
import com.ksw.service.forObject.object.CategoryService;
import com.ksw.service.forObject.object.FileService;
import com.ksw.service.forObject.object.NoteService;
import com.ksw.service.forObject.object.ReplyService;
import com.ksw.service.forObject.object.UserService;
import com.ksw.service.function.QuestionService;

@Controller
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	@Autowired
	private NoteService noteService;
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private FileService fileService;
	@Autowired
	private ReplyService replyService;
	
	@GetMapping("/write")
	public String toWritePage() {
		return "write";
	}
	
	@GetMapping("/view")
	public String toViewPage(
			RedirectAttributes redirectAttributes,
			HttpSession session,
			@SessionAttribute("questionVO") QuestionVO questionVO, 
			@RequestParam("noteNo") Integer noteNo) {
		if (questionVO != null) {
			return "view"; 
		} else {
			try {
				QuestionVO newQuestionVO = questionService.noteRead(noteNo);
				session.setAttribute("questionVO", newQuestionVO);
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("error", "조회 실패");
			}
		}
		return "view";
	}
	
	@PostMapping("/write")
	public String notewrite(
			@ModelAttribute NoteDTO noteDTO,
			@ModelAttribute CategoryDTO categoryDTO,
			@ModelAttribute UserDTO userDTO,
			@RequestParam("file") MultipartFile file,
			HttpSession session,
			RedirectAttributes redirectAttributes) {

            try {
            	QuestionVO questionVO = questionService.noteWrite(noteDTO, file, categoryDTO, userDTO);
            	session.setAttribute("questionVO", questionVO);
                redirectAttributes.addFlashAttribute("message", "쓰기 성공");
            } catch (Exception e) {
            	redirectAttributes.addFlashAttribute("error", "쓰기 실패");
            }	
		return "redirect:/view";
	}
}
