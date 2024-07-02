package com.ksw.service.object;

import com.ksw.dto.forObject.ViewUserNoteDTO;
import com.ksw.object.entity.mybatis.ViewUserNote;
import com.ksw.object.vo.ViewUserNoteVO;
import org.springframework.stereotype.Service;

@Service
public class ViewUserNoteService {

    // Entity -> DTO 변환 메소드
    public ViewUserNoteDTO convertToDTO(ViewUserNote viewUserNoteEntity) {
        return new ViewUserNoteDTO.Builder()
                .userNo(viewUserNoteEntity.getUserNo())
                .noteNo(viewUserNoteEntity.getNoteNo())
                .noteViewNo(viewUserNoteEntity.getPostViewNo())
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
