package com.ksw.service.forObject.relation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	private AnswerHistoryDTO convertToDTO(AnswerHistory answerHistory) {
		AnswerHistoryDTO answerHistoryDTO = new AnswerHistoryDTO();
		answerHistoryDTO.setNoteDTO(noteService.convertToDTO(answerHistory.getNote()));
		answerHistoryDTO.setAnswerDTO(answerService.convertToDTO(answerHistory.getAnswer()));
		answerHistoryDTO.setUserDTO(userService.convertToDTO(answerHistory.getUser()));
		answerHistoryDTO.setCreatedAt(answerHistory.getCreatedAt());
		answerHistoryDTO.setUpdatedAt(answerHistory.getUpdatedAt());
		return answerHistoryDTO;
	}

    private AnswerHistoryVO convertToVO(AnswerHistoryDTO answerHistoryDTO) {
        return new AnswerHistoryVO.Builder()
                .noteVO(noteService.convertToVO(answerHistoryDTO.getNoteDTO()))
                .answerVO(answerService.convertToVO(answerHistoryDTO.getAnswerDTO()))
                .userVO(userService.convertToVO(answerHistoryDTO.getUserDTO()))
                .createdAt(answerHistoryDTO.getCreatedAt())
                .updatedAt(answerHistoryDTO.getUpdatedAt())
                .build();
    }
}