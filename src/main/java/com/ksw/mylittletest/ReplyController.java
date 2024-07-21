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
import com.ksw.dto.forObject.entity.UserDTO;
import com.ksw.object.entity.Alarm;
import com.ksw.object.entity.Reply;
import com.ksw.object.entity.User;
import com.ksw.service.forObject.entity.AlarmService;
import com.ksw.service.forObject.entity.ReplyService;
import com.ksw.service.forObject.relation.AlarmRelationService;
import com.ksw.service.forObject.relation.NoteReplyService;
import com.ksw.service.forObject.relation.NoteUserService;
import com.ksw.service.forObject.relation.ReplyUserService;
import com.ksw.service.function.AuthService;
import com.ksw.vo.forObject.entity.UserVO;

@Controller
public class ReplyController {

	private Logger logger= LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ReplyService replyService;
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
	@Autowired
	private AlarmService alarmService;
	@Autowired
	private AlarmRelationService alarmRelationService;
	@Autowired
	private NoteUserService noteUserService;
	
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
		
		Reply reply = replyService.convertToEntity(replyDTO);
		replyDTO = replyService.writeReply(replyDTO);
		
		reply = replyRepository.getById(replyDTO.getReplyNo());
		User user = userRepository.getById(userVO.getUserNo());
		
		replyUserService.writeReplyRelation(reply.getReplyNo(), user.getUserNo());
		noteReplyService.writeReplyRelation(reply.getReplyNo(), noteNo);
		
		Alarm alarm = alarmService.save(2);
		UserDTO writer = noteUserService.getUserByNoteNo(noteNo);
		alarmRelationService.insert(alarm.getAlarmNo(), writer.getUserNo(), userVO.getUserNo(), noteNo, null);
		
		return "redirect:/myTest/category/"+categoryTitle+"/"+noteNo;
	}
}
