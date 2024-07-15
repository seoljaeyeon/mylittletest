package com.ksw.mylittletest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ksw.dto.forObject.entity.ReplyDTO;
import com.ksw.service.forObject.entity.ReplyService;
import com.ksw.service.forObject.entity.UserService;
import com.ksw.service.forObject.relation.ReplyUserService;
import com.ksw.service.function.AuthService;
import com.ksw.vo.forObject.entity.UserVO;

@Controller
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private ReplyUserService replyUserService;

	@Autowired
	private AuthService authService;
	
	
	@PostMapping("/replyWrite/{noteNo}") 
	public String replyWrite(
            @PathVariable("noteNo") Integer noteNo,
			@ModelAttribute ReplyDTO replyDTO,
			HttpServletRequest request) {
		
		UserVO userVO = authService.getUserVO();
		
		replyService.writeReply(replyDTO);
		replyUserService.writeReplyRelation(replyDTO, userService.convertVOToDTO(userVO)); 
		
		return "redirect:/view/"+noteNo;
	}
}
