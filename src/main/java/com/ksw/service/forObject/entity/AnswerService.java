package com.ksw.service.forObject.entity;

import com.ksw.dao.forObject.entity.AnswerRepository;
import com.ksw.dto.forObject.entity.*;
import com.ksw.object.entity.Answer;
import com.ksw.vo.forObject.entity.AnswerVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerService {

    public Answer convertToEntity(AnswerDTO answerDTO) {
    	Answer answer = new Answer();
    	if (answerDTO == null) {
    		System.out.println("AnswerDTO to Answer failed. Empty Answer created. AnswerDTO is null");
    		return answer;
    	}
        answer.setAnswerNo(answerDTO.getAnswerNo());
        answer.setAnswerType(answerDTO.getAnswerType());
        answer.setCreatedAt(answerDTO.getCreatedAt());
        return answer;
    }

    public AnswerDTO convertToDTO(Answer answer) {
    	AnswerDTO answerDTO = new AnswerDTO();
    	if (answer == null) {
    		System.out.println("Answer to AnswerDTO failed. Empty AnswerDTO created. Answer is null");
    		return answerDTO;
    	}
        answerDTO.setAnswerNo(answer.getAnswerNo());
        answerDTO.setAnswerType(answer.getAnswerType());
        answerDTO.setCreatedAt(answer.getCreatedAt());
        return answerDTO;
    }

    public AnswerVO convertToVO(AnswerDTO answerDTO) {
    	if (answerDTO == null) {
    		System.out.println("AnswerDTO to AnswerVO failed. Empty AnswerVO created. AnswerDTO is null");
    		return new AnswerVO.Builder().build();
    	}
        return new AnswerVO.Builder()
                .answerNo(answerDTO.getAnswerNo())
                .answerType(answerDTO.getAnswerType())
                .createdAt(answerDTO.getCreatedAt())
                .build();
    }
}
