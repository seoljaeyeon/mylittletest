package com.ksw.mylittletest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ksw.dto.forObject.object.ReplyDTO;
import com.ksw.dto.forObject.object.UserDTO;
import com.ksw.dto.function.QuestionDTO;
import com.ksw.object.entity.jpa.Reply;
import com.ksw.service.forObject.object.ReplyService;
import com.ksw.service.forObject.relation.NoteReplyService;
import com.ksw.service.forObject.relation.ReplyUserService;

@Controller
public class ReplyController {

	@Autowired
	private NoteReplyService noteReplyService; 
	
	@Autowired
	private ReplyService replyService;
	
	@Autowired
	private ReplyService userService;
	
	@Autowired 
	private ReplyUserService userReplyService;
	
	@PostMapping("/replyWrite")
	public String replyWrite(
			@ModelAttribute QuestionDTO questionDTO,
			@ModelAttribute ReplyDTO replyDTO,
			@ModelAttribute UserDTO userDTO) {
		
		Reply reply = replyService.convertToEntity(replyDTO);
		
		return "redirect:/view";
	}
	
	
	
}
