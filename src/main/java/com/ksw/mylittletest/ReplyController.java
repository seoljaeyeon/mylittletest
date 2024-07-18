package com.ksw.mylittletest;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ksw.dao.forObject.entity.ReplyRepository;
import com.ksw.dao.forObject.entity.UserRepository;
import com.ksw.dto.forObject.entity.ReplyDTO;
import com.ksw.object.entity.Reply;
import com.ksw.object.entity.User;
import com.ksw.service.forObject.entity.ReplyService;
import com.ksw.service.forObject.entity.UserService;
import com.ksw.service.forObject.relation.NoteReplyService;
import com.ksw.service.forObject.relation.ReplyUserService;
import com.ksw.service.function.AuthService;
import com.ksw.vo.forObject.entity.UserVO;

@Controller
public class ReplyController {

	private Logger logger= LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ReplyService replyService;
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private ReplyUserService replyUserService;

	@Autowired
	private AuthService authService;
	
	@Autowired
	private NoteReplyService noteReplyService;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/replyWrite") 
	@Transactional
	public String replyWrite(
			@ModelAttribute ReplyDTO replyDTO,
			@RequestParam("noteNo") Integer noteNo,
			@RequestParam("categoryTitle") String categoryTitle,
			Model model,
			HttpServletRequest request) {
		
	    // 사용자 인증 정보 가져오기
	    UserVO userVO = authService.getUserVO();
	    if (userVO == null || userVO.getUserNo() == null) {
	        return "redirect:/login";
	    }
	    
		if(noteNo == null || categoryTitle == null) {
			return "redirect:/myTest/category";
		}
		
		System.out.println("/replyWrite 진입. replyDTO 정보 확인 : " + replyDTO.getReplyContent());
		System.out.println("/replyWrite 진입. noteNo 정보 확인 : " + noteNo);
		System.out.println("/replyWrite 진입. categoryTitle 정보 확인 : " + categoryTitle);
		
		
		Reply reply = replyService.convertToEntity(replyDTO);
		replyDTO = replyService.writeReply(replyDTO);
		System.out.println("replyDTO 값이 비었는가?"+replyDTO.getReplyNo());
		System.out.println("UserVO 값이 비었는가? "+userVO.getUserNo());
		
		reply = replyRepository.getById(replyDTO.getReplyNo());
		User user = userRepository.getById(userVO.getUserNo());
		
		System.out.println("reply 값이 비었는가?"+reply.getReplyNo());
		System.out.println("user 값이 비었는가?"+user.getUserNo());
		
		logger.error(this.getClass().getName() + "works");
		
		replyUserService.writeReplyRelation(reply.getReplyNo(), user.getUserNo());
		noteReplyService.writeReplyRelation(reply.getReplyNo(), noteNo);
		
		return "redirect:/myTest/category/"+categoryTitle+"/"+noteNo;
	}
}
