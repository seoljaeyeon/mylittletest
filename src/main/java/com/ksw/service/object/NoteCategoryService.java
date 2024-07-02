package com.ksw.service.object;

import com.ksw.dto.forObject.NoteCategoryDTO;
import com.ksw.object.entity.mybatis.NoteCategory;
import com.ksw.object.vo.NoteCategoryVO;
import org.springframework.stereotype.Service;

@Service
public class NoteCategoryService {

    // Entity -> DTO 변환 메소드
    public NoteCategoryDTO convertToDTO(NoteCategory noteCategoryEntity) {
        return new NoteCategoryDTO.Builder()
                .categoryNo(noteCategoryEntity.getCategoryNo())
                .noteNo(noteCategoryEntity.getNoteNo())
                .build();
    }

    // DTO -> VO 변환 메소드
    public NoteCategoryVO convertToVO(NoteCategoryDTO noteCategoryDTO) {
        return new NoteCategoryVO.Builder()
                .categoryNo(noteCategoryDTO.getCategoryNo())
                .noteNo(noteCategoryDTO.getNoteNo())
                .build();
    }
}
