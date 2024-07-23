package com.ksw.mylittletest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
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
		if (userVO == null) {
			return "redirect:/login";
		}
		
		// 사용자 정보 저장
		model.addAttribute("userVO", userVO);
		return "write";
	}
	
  @PostMapping(value = "/write", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
	public Map<String, String> notewrite(
			@ModelAttribute NoteDTO noteDTO,
			@ModelAttribute CategoryDTO categoryDTO,
            @RequestParam(value = "file", required = false) List<MultipartFile> files,
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
            	QuestionVO questionVO = questionService.Write(noteDTO, files, categoryDTO, userVO);
            	model.addAttribute("questionVO", questionVO); // view에서 어떻게 쓸 지 아직 미정
                response.put("status", "success");
                response.put("url", "/mylittletest/mytest/category/" + questionVO.getCategoryVO().getCategoryTitle() + "/" + questionVO.getNoteVO().getNoteNo());
                return response;
                
            } catch (Exception e) {
                response.put("status", "fail");
                response.put("url", "/mylittletest/write");
            	return response;
            }	
	}
  
	@GetMapping("/modify")
	public String noParameter()
	{
		return "redirect:/login";
	}
	
	@GetMapping("/modify/{noteNo}")
	public String toModifyPage(
			@PathVariable Integer noteNo,
			@RequestParam(value = "menuName", required  = false ) String menuName,
			Model model,
			HttpServletRequest request,
			HttpSession session
			) {
		UserVO userVO = authService.getUserVO();
		if (userVO == null) {
			return "redirect:/login";
		}
		
		if (noteNo == null) {
			return "redirect:/login";
		}
		
		if (menuName == null) {
			return "redirect:/login";
		}
		
		session.setAttribute("fromModify", true);
		QuestionVO questionVO = questionService.Read(noteNo, userVO, request, session);
		
		// 사용자 정보 저장
		model.addAttribute("modify", true);
		model.addAttribute("userVO", userVO);
		model.addAttribute("menuName", menuName);
		model.addAttribute("questionVO", questionVO);
		
		return "write";
	}
}
