package com.ksw.service.forObject.relation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dao.forObject.relation.AnswerHistoryMapper;
import com.ksw.dto.forObject.relation.AnswerHistoryDTO;
import com.ksw.object.relation.AnswerHistory;
import com.ksw.service.forObject.entity.AnswerService;
import com.ksw.service.forObject.entity.NoteService;
import com.ksw.service.forObject.entity.UserService;
import com.ksw.vo.forObject.relation.AnswerHistoryVO;

@Service
public class AnswerHistoryService {

	@Autowired
	private NoteService noteService;
	@Autowired
	private AnswerService answerService;
	@Autowired
	private UserService userService;
	
	@Autowired
	private AnswerHistoryMapper answerHistoryMapper;
	
	public Integer getAnswerHistoryByNoteNoAndUserNo(Integer noteNo, Integer userNo) {
		Integer result = answerHistoryMapper.findAnswerByNoteNoAndUserNo(noteNo, userNo);
		
		return result;
	}
	
	public AnswerHistoryDTO convertToDTO(AnswerHistory answerHistory) {
		AnswerHistoryDTO answerHistoryDTO = new AnswerHistoryDTO();
		answerHistoryDTO.setNoteDTO(noteService.convertToDTO(answerHistory.getNote()));
		answerHistoryDTO.setAnswerDTO(answerService.convertToDTO(answerHistory.getAnswer()));
		answerHistoryDTO.setUserDTO(userService.convertToDTO(answerHistory.getUser()));
		return answerHistoryDTO;
	}

    public AnswerHistoryVO convertToVO(AnswerHistoryDTO answerHistoryDTO) {
        return new AnswerHistoryVO.Builder()
                .noteVO(noteService.convertToVO(answerHistoryDTO.getNoteDTO()))
                .answerVO(answerService.convertToVO(answerHistoryDTO.getAnswerDTO()))
                .userVO(userService.convertToVO(answerHistoryDTO.getUserDTO()))
                .build();
    }
}