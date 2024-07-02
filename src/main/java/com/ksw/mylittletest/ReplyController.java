package com.ksw.mylittletest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

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
	private ReplyUserService userReplyService;
	
	@PostMapping("/replyWrite")
	public String replyWrite() {
		
	
		
		return "redirect:/view";
	}
	
}
