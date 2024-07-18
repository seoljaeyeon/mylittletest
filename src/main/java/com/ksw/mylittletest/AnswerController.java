package com.ksw.mylittletest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ksw.dto.forObject.entity.AnswerDTO;
import com.ksw.service.forObject.entity.AnswerService;
import com.ksw.service.forObject.relation.AnswerHistoryService;
import com.ksw.service.function.AuthService;
import com.ksw.vo.forObject.entity.UserVO;

@Controller
public class AnswerController {

	@Autowired
	private AnswerHistoryService answerHistoryService;
	@Autowired
	private AnswerService answerService;
	@Autowired
	private AuthService authService;
	
	@PostMapping(value="/answer", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Transactional
	public Map<String, String> answerInsert(
			@ModelAttribute AnswerDTO answerDTO, // 2 - 정답으로 체크한 것 / 1 - 오답으로 체크한 것 
			@ModelAttribute Integer noteNo,
			Model model
			) {
		Map<String, String> response = new HashMap<>();
		
		UserVO userVO = authService.getUserVO();
		
		if (userVO == null || userVO.getUserNo() == null) {
			response.put("status", "loing_needed");
			response.put("url", "/mylittletest/login");
			return response;
		}
		
		if(answerDTO == null) {
			response.put("status", "input_null");
			return response;
		}
		// 사용자 정보model에 추가
		model.addAttribute("userVO", userVO);
		
		try {
			// answerHistory createdAt 지우
			AnswerDTO answer = answerService.save(answerService.convertToEntity(answerDTO));
			Integer result = answerHistoryService.insertHistory(noteNo, answer.getAnswerNo(), userVO.getUserNo());
			model.addAttribute("stauts", "success");
		} catch (Exception e) {
			model.addAttribute("status", "failed");
		}
		return response;
	}
	
}
