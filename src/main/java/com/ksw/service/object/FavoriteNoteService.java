package com.ksw.service.object;

import com.ksw.dto.forObject.FavoriteNoteDTO;
import com.ksw.object.entity.mybatis.FavoriteNote;
import com.ksw.object.vo.FavoriteNoteVO;
import org.springframework.stereotype.Service;

@Service
public class FavoriteNoteService {

    // Entity -> DTO 변환 메소드
    public FavoriteNoteDTO convertToDTO(FavoriteNote favoriteNoteEntity) {
        return new FavoriteNoteDTO.Builder()
                .userNo(favoriteNoteEntity.getUserNo())
                .noteNo(favoriteNoteEntity.getNoteNo())
                .favoriteNo(favoriteNoteEntity.getFavoriteNo())
                .build();
    }

    // DTO -> VO 변환 메소드
    public FavoriteNoteVO convertToVO(FavoriteNoteDTO favoriteNoteDTO) {
        return new FavoriteNoteVO.Builder()
                .userNo(favoriteNoteDTO.getUserNo())
                .noteNo(favoriteNoteDTO.getNoteNo())
                .favoriteNo(favoriteNoteDTO.getFavoriteNo())
                .build();
    }
}
