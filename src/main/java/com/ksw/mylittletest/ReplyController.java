package com.ksw.mylittletest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ksw.dto.forObject.entity.ReplyDTO;
import com.ksw.dto.forObject.entity.UserDTO;
import com.ksw.dto.forObject.relation.ReplyUserDTO;
import com.ksw.service.forObject.entity.ReplyService;
import com.ksw.service.forObject.entity.UserService;
import com.ksw.service.forObject.relation.ReplyUserService;
import com.ksw.service.function.CertifiedUserDetails;

@Controller
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private ReplyUserService replyUserService;
	
	
	@PostMapping("/replyWrite/{noteNo}") 
	public String replyWrite(
			@AuthenticationPrincipal CertifiedUserDetails userinfo,
            @PathVariable("noteNo") Integer noteNo,
			@ModelAttribute ReplyDTO replyDTO,
			HttpServletRequest request) {
		
		UserDTO userDTO = userService.convertVOToDTO(userinfo.getUserVO());
		replyService.writeReply(replyDTO);
		replyUserService.writeReplyRelation(replyDTO, userDTO); 
		
		return "redirect:/view/"+noteNo;
	}
}
