package com.ksw.service.forObject.relation;

import com.ksw.dao.forObject.relation.ViewUserNoteMapper;
import com.ksw.dto.forObject.relation.ViewUserNoteDTO;
import com.ksw.object.relation.ViewUserNote;
import com.ksw.vo.forObject.relation.ViewUserNoteVO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ViewUserNoteService {
	
    // Entity -> DTO 변환 메소드
    public ViewUserNoteDTO convertToDTO(ViewUserNote viewUserNoteEntity) {
        return new ViewUserNoteDTO.Builder()
                .userNo(viewUserNoteEntity.getUserNo())
                .noteNo(viewUserNoteEntity.getNoteNo())
                .noteViewNo(viewUserNoteEntity.getNoteViewNo())
                .build();
    }

    // DTO -> VO 변환 메소드
    public ViewUserNoteVO convertToVO(ViewUserNoteDTO viewUserNoteDTO) {
        return new ViewUserNoteVO.Builder()
                .userNo(viewUserNoteDTO.getUserNo())
                .noteNo(viewUserNoteDTO.getNoteNo())
                .noteViewNo(viewUserNoteDTO.getPostViewNo())
                .build();
    }
}
