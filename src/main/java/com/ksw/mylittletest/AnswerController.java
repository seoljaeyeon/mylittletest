package com.ksw.mylittletest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	        @RequestBody Map<String, Object> payload, // JSON 데이터 받기
			Model model
			) {
		Map<String, String> response = new HashMap<>();
		
		UserVO userVO = authService.getUserVO();

	    Integer answerType = (Integer) payload.get("answerType");
	    System.out.println(payload.get("answerType"));
	    Integer noteNo = (Integer) payload.get("noteNo");
	    System.out.println(payload.get("noteNo"));
		
		if (userVO == null || userVO.getUserNo() == null) {
			response.put("status", "loing_needed");
			response.put("url", "/mylittletest/login");
			return response;
		}
		
		if(answerType == null) {
			response.put("status", "input_null");
			return response;
		}
	    
		// 사용자 정보model에 추가
		model.addAttribute("userVO", userVO);
		
		try {
			AnswerDTO answerDTO = new AnswerDTO();
			Integer answerNo = answerHistoryService.getAnswerNoByNoteNoAndUserNo(noteNo, userVO.getUserNo());
			if (answerNo != null) {
				answerDTO.setAnswerNo(answerNo);
			}
	        answerDTO.setAnswerType(answerType);
	        AnswerDTO answer = answerService.save(answerService.convertToEntity(answerDTO));
	        Integer result = (answerNo == null)
	                ? answerHistoryService.insertHistory(noteNo, answer.getAnswerNo(), userVO.getUserNo(), answer.getCreatedAt())
	                : answerHistoryService.updateHistory(noteNo, answer.getAnswerNo(), userVO.getUserNo());
	        System.out.println("문제없음");
	        response.put("status", "success");
	    } catch (Exception e) {
	        response.put("status", "failed");
	        e.printStackTrace();
	        System.out.println("문제있음");
	    }
	    return response;
	}
}
