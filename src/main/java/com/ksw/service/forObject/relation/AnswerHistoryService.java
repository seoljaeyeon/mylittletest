package com.ksw.service.forObject.relation;

import java.util.List;
import java.util.Map;

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
	
	public List<Map<String,Object>> getCategoryListByUserNoAndAnswerType(Integer userNo, Integer answerNo) {
		return answerHistoryMapper.getCategoryListByUserNoAndAnswerType(userNo, answerNo);
	}
	
	public List<Map<String, Object>> getNoteListByUserNoAndAnswerType(Integer userNo, Integer AnswerType) {
		List<Map<String, Object>> result = answerHistoryMapper.getNoteListByUserNoAndAnswerType(userNo, AnswerType);
		return result;
	}
	
	public Integer updateHistory(Integer noteNo, Integer answerNo, Integer userNo) {
		if (noteNo == null || answerNo == null || userNo == null) {
			return -1;
		}
		
		Integer result = answerHistoryMapper.updateHistory(noteNo, answerNo, userNo);
		return result;	
	}
	
	public Integer insertHistory(Integer noteNo, Integer answerNo, Integer userNo) {
		if (noteNo == null || answerNo == null || userNo == null) {
			return -1;
		}
		
		Integer result = answerHistoryMapper.insertHistory(noteNo, answerNo, userNo);
		return result;
	}
		
	
	public Integer getAnswerNoByNoteNoAndUserNo(Integer noteNo, Integer userNo) {
		if(noteNo == null || userNo == null) {
			return -1;
		}
		Integer result = answerHistoryMapper.getAnswerNoByNoteNoAndUserNo(noteNo, userNo);
		return result;
	}
	
	public Integer getAnswerHistoryByNoteNoAndUserNo(Integer noteNo, Integer userNo) {
		if (noteNo == null || userNo == null) {
			return -1; // -1은 null 대신 반환값으로 사용
		}
		Integer result = answerHistoryMapper.findAnswerByNoteNoAndUserNo(noteNo, userNo);
		return result;
	}
	
	public AnswerHistoryDTO convertToDTO(AnswerHistory answerHistory) {
		AnswerHistoryDTO answerHistoryDTO = new AnswerHistoryDTO();
		if(answerHistory == null) {
    		System.out.println("AnswerHistory to AnswerHistoryDTO failed. Empty AnswerHistoryDTO created. AnswerHistory is null");
    		return answerHistoryDTO;
		}
		answerHistoryDTO.setNoteDTO(noteService.convertToDTO(answerHistory.getNote()));
		answerHistoryDTO.setAnswerDTO(answerService.convertToDTO(answerHistory.getAnswer()));
		answerHistoryDTO.setUserDTO(userService.convertToDTO(answerHistory.getUser()));
		return answerHistoryDTO;
	}

    public AnswerHistoryVO convertToVO(AnswerHistoryDTO answerHistoryDTO) {
    	if (answerHistoryDTO == null) {
    		System.out.println("AnswerHistoryDTO to AnswerHistoryVO failed. Empty AnswerHistoryVO created. AnswerHistoryDTO is null");    		
    		return new AnswerHistoryVO.Builder().build();
    	}
        return new AnswerHistoryVO.Builder()
                .noteVO(noteService.convertToVO(answerHistoryDTO.getNoteDTO()))
                .answerVO(answerService.convertToVO(answerHistoryDTO.getAnswerDTO()))
                .userVO(userService.convertToVO(answerHistoryDTO.getUserDTO()))
                .build();
    }
}