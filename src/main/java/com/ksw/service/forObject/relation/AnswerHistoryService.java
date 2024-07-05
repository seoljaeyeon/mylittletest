package com.ksw.service.forObject.relation;

import com.ksw.dao.forObject.relation.AnswerHistoryMapper;
import com.ksw.dto.forObject.entity.AnswerDTO;
import com.ksw.dto.forObject.relation.AnswerHistoryDTO;
import com.ksw.object.relation.AnswerHistory;
import com.ksw.service.forObject.entity.AnswerService;
import com.ksw.vo.forObject.relation.AnswerHistoryVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerHistoryService {

    @Autowired
    private AnswerHistoryMapper answerHistoryMapper;

    @Autowired
    private AnswerService answerService;

    @Transactional
    public AnswerHistoryDTO createAnswerHistory(AnswerHistoryDTO answerHistoryDTO) {
        AnswerHistory answerHistory = convertToEntity(answerHistoryDTO);
        answerHistoryMapper.insert(answerHistory);
        return convertToDTO(answerHistory);
    }

    @Transactional(readOnly = true)
    public AnswerHistoryDTO getAnswerHistoryByNoteNoAndUserNo(Integer noteNo, Integer userNo) {
        List<AnswerHistory> answerHistories = answerHistoryMapper.findByNoteNoAndUserNo(noteNo, userNo);
        if (answerHistories != null && !answerHistories.isEmpty()) {
            AnswerHistory latestAnswerHistory = answerHistories.stream()
                .max(Comparator.comparing(AnswerHistory::getUpdatedAt))
                .orElse(null);
            return convertToDTO(latestAnswerHistory);
        }
        return null;
    }

    @Transactional(readOnly = true)
    public List<AnswerHistoryVO> getAllAnswerHistories() {
        return answerHistoryMapper.findAll().stream()
                .map(this::convertToDTO)
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Integer getAnswerTypeByNoteNo(Integer noteNo) {
        List<AnswerHistory> answerHistories = answerHistoryMapper.findByNoteNo(noteNo);
        if (answerHistories != null && !answerHistories.isEmpty()) {
            AnswerHistory latestAnswerHistory = answerHistories.stream()
                .max(Comparator.comparing(AnswerHistory::getUpdatedAt))
                .orElse(null);
            if (latestAnswerHistory != null) {
                AnswerDTO answerDTO = answerService.getAnswerByNo(latestAnswerHistory.getAnswerNo());
                return answerDTO != null ? answerDTO.getAnswerType() : null;
            }
        }
        return null;
    }

    @Transactional
    public void updateAnswerHistory(AnswerHistoryDTO answerHistoryDTO) {
        AnswerHistory answerHistory = convertToEntity(answerHistoryDTO);
        answerHistoryMapper.update(answerHistory);
    }

    @Transactional
    public void deleteAnswerHistory(Integer noteNo, Integer userNo) {
        answerHistoryMapper.delete(noteNo, userNo);
    }

    private AnswerHistory convertToEntity(AnswerHistoryDTO answerHistoryDTO) {
        AnswerHistory answerHistory = new AnswerHistory();
        answerHistory.setNoteNo(answerHistoryDTO.getNoteNo());
        answerHistory.setAnswerNo(answerHistoryDTO.getAnswerNo());
        answerHistory.setUserNo(answerHistoryDTO.getUserNo());
        answerHistory.setCreatedAt(answerHistoryDTO.getCreatedAt());
        answerHistory.setUpdatedAt(answerHistoryDTO.getUpdatedAt());
        return answerHistory;
    }

    private AnswerHistoryDTO convertToDTO(AnswerHistory answerHistory) {
        AnswerHistoryDTO answerHistoryDTO = new AnswerHistoryDTO();
        answerHistoryDTO.setNoteNo(answerHistory.getNoteNo());
        answerHistoryDTO.setAnswerNo(answerHistory.getAnswerNo());
        answerHistoryDTO.setUserNo(answerHistory.getUserNo());
        answerHistoryDTO.setCreatedAt(answerHistory.getCreatedAt());
        answerHistoryDTO.setUpdatedAt(answerHistory.getUpdatedAt());
        return answerHistoryDTO;
    }

    private AnswerHistoryVO convertToVO(AnswerHistoryDTO answerHistoryDTO) {
        return new AnswerHistoryVO.Builder()
                .noteNo(answerHistoryDTO.getNoteNo())
                .answerNo(answerHistoryDTO.getAnswerNo())
                .userNo(answerHistoryDTO.getUserNo())
                .createdAt(answerHistoryDTO.getCreatedAt())
                .updatedAt(answerHistoryDTO.getUpdatedAt())
                .build();
    }
}