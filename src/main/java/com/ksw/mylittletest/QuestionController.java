package com.ksw.mylittletest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ksw.dto.forObject.CategoryDTO;
import com.ksw.dto.forObject.FileDTO;
import com.ksw.dto.forObject.NoteDTO;
import com.ksw.dto.forObject.UserDTO;
import com.ksw.object.vo.CategoryVO;
import com.ksw.object.vo.FileVO;
import com.ksw.object.vo.NoteVO;
import com.ksw.object.vo.UserVO;
import com.ksw.object.vo.QuestionVO;
import com.ksw.service.function.QuestionService;
import com.ksw.service.object.CategoryService;
import com.ksw.service.object.FileService;
import com.ksw.service.object.NoteService;
import com.ksw.service.object.ReplyService;
import com.ksw.service.object.UserService;

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
	
	@PostMapping("/write")
	public String notewrite(
			@ModelAttribute NoteDTO noteDTO,
			@ModelAttribute CategoryDTO categoryDTO,
			@ModelAttribute UserDTO userDTO,
			@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {

            try {
            	QuestionVO questionVO = questionService.noteWrite(noteDTO, file, categoryDTO, userDTO);
            	redirectAttributes.addFlashAttribute("writeVO", questionVO);
                redirectAttributes.addFlashAttribute("message", "Write successful!");
            } catch (Exception e) {
            	redirectAttributes.addFlashAttribute("error", "Write failed");
            }	
		return "redirect:/view";
	}
}
