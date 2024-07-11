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

    @Autowired
    private AnswerRepository answerRepository;

    @Transactional
    public AnswerDTO createAnswer(AnswerDTO answerDTO) {
        Answer answer = convertToEntity(answerDTO);
        answer = answerRepository.save(answer);
        return convertToDTO(answer);
    }
    
    @Transactional(readOnly = true)
    public AnswerDTO getAnswerByNo(Integer answerNo) {
        Answer answer = answerRepository.findById(answerNo).orElse(null);
        return answer != null ? convertToDTO(answer) : null;
    }

    public Answer convertToEntity(AnswerDTO answerDTO) {
        Answer answer = new Answer();
        answer.setAnswerNo(answerDTO.getAnswerNo());
        answer.setAnswerType(answerDTO.getAnswerType());
        answer.setCreatedAt(answerDTO.getCreatedAt());
        return answer;
    }

    public AnswerDTO convertToDTO(Answer answer) {
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setAnswerNo(answer.getAnswerNo());
        answerDTO.setAnswerType(answer.getAnswerType());
        answerDTO.setCreatedAt(answer.getCreatedAt());
        return answerDTO;
    }

    public AnswerVO convertToVO(AnswerDTO answerDTO) {
        return new AnswerVO.Builder()
                .answerNo(answerDTO.getAnswerNo())
                .answerType(answerDTO.getAnswerType())
                .createdAt(answerDTO.getCreatedAt())
                .build();
    }
}
