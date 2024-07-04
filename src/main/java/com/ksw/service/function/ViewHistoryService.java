package com.ksw.service.function;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ksw.dao.object.NoteViewRepository;
import com.ksw.dao.relation.NoteCategoryMapper;
import com.ksw.dao.relation.ViewUserNoteMapper;
import com.ksw.dto.function.ViewHistoryDTO;
import com.ksw.object.entity.jpa.NoteView;
import com.ksw.object.entity.mybatis.ViewUserNote;
import com.ksw.object.vo.function.ViewHistoryVO;

@Service
public class ViewHistoryService {

    @Autowired
    private NoteViewRepository noteViewRepository;

    @Autowired
    private ViewUserNoteMapper viewUserNoteMapper;
	
    @Autowired
    private NoteCategoryMapper noteCategoryMapper;
    
    @Transactional(readOnly = true)
    public List<ViewHistoryVO> getHistory(int userNo) {
        List<ViewUserNote> viewUserNotes = viewUserNoteMapper.findByUserNo(userNo);
        return viewUserNotes.stream()
                .map(this::convertToDTO)
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ViewHistoryVO> getHistoryByCategory(Integer userNo, Integer categoryNo) {
        // 특정 카테고리의 모든 노트 ID를 가져옵니다.
        List<Integer> noteNosInCategory = noteCategoryMapper.findNoteNosByCategoryNo(categoryNo);

        // 사용자가 이미 본 노트 ID를 가져옵니다.
        List<ViewUserNote> viewUserNotes = viewUserNoteMapper.findByUserNo(userNo);

        // 카테고리 내에서 사용자가 본 노트들만 필터링합니다.
        List<ViewUserNote> filteredViewUserNotes = viewUserNotes.stream()
                .filter(viewUserNote -> noteNosInCategory.contains(viewUserNote.getNoteNo()))
                .collect(Collectors.toList());

        return filteredViewUserNotes.stream()
                .map(this::convertToDTO)
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }
    
    private ViewHistoryDTO convertToDTO(ViewUserNote viewUserNote) {
        NoteView noteView = noteViewRepository.findById(viewUserNote.getNoteViewNo()).orElse(null);
        if (noteView != null) {
            ViewHistoryDTO dto = new ViewHistoryDTO();
            dto.setNoteNo(viewUserNote.getNoteNo());
            dto.setNoteViewNo(viewUserNote.getNoteViewNo());
            dto.setCreateAt(noteView.getCreatedAt());
            return dto;
        }
        return null;
    }
    
    public ViewHistoryVO convertToVO(ViewHistoryDTO viewHistoryDTO) {
        return new ViewHistoryVO.Builder()
                .noteNo(viewHistoryDTO.getNoteNo())
                .noteViewNo(viewHistoryDTO.getNoteViewNo())
                .createAt(viewHistoryDTO.getCreateAt())
                .build();
    }
}

