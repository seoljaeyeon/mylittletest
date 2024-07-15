package com.ksw.mylittletest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ksw.dto.forObject.entity.ReplyDTO;
import com.ksw.service.forObject.entity.ReplyService;
import com.ksw.service.forObject.entity.UserService;
import com.ksw.service.forObject.relation.ReplyUserService;
import com.ksw.service.function.AuthService;
import com.ksw.vo.forObject.entity.UserVO;
import com.ksw.vo.function.QuestionVO;

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
	
	
	@PostMapping("/replyWrite") 
	public String replyWrite(
			@ModelAttribute ReplyDTO replyDTO,
			Model model,
			HttpServletRequest request) {
		
	    // 사용자 인증 정보 가져오기
	    UserVO userVO = authService.getUserVO();
	    if (userVO == null) {
	        return "redirect:/login";
	    }
	    
		QuestionVO questionVO = (QuestionVO) model.getAttribute("questionVO");
		if(questionVO == null) {
			return "redirect:/mytest/category";
		}
		
		replyService.writeReply(replyDTO);
		replyUserService.writeReplyRelation(replyDTO, userService.convertVOToDTO(userVO)); 
		
		return "redirect:/myTest/category/"+questionVO.getCategoryVO().getCategoryTitle()+"/"+questionVO.getNoteVO().getNoteNo();
	}
}
