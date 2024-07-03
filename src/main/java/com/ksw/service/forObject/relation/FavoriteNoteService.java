package com.ksw.service.forObject.relation;

import com.ksw.dao.relation.FavoriteNoteMapper;
import com.ksw.dto.forObject.relation.FavoriteNoteDTO;
import com.ksw.object.entity.mybatis.FavoriteNote;
import com.ksw.object.vo.relation.FavoriteNoteVO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FavoriteNoteService {
	
    @Autowired
    private FavoriteNoteMapper favoriteNoteMapper;

    @Transactional(readOnly = true)
    public List<Integer> findDislikedNoteNosByUserNo(Integer userNo) {
        return favoriteNoteMapper.findDislikedNoteNosByUserNo(userNo);
    }

    @Transactional
    public void addFavoriteNote(FavoriteNote favoriteNote) {
        favoriteNoteMapper.insert(favoriteNote);
    }

    @Transactional
    public void removeFavoriteNote(Integer userNo, Integer noteNo) {
        favoriteNoteMapper.delete(userNo, noteNo);
    }

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
