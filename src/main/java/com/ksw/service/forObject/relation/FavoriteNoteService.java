package com.ksw.service.forObject.relation;

import com.ksw.dao.relation.FavoriteNoteMapper;
import com.ksw.dto.forObject.relation.FavoriteNoteDTO;
import com.ksw.object.entity.jpa.Favorite;
import com.ksw.object.entity.mybatis.FavoriteNote;
import com.ksw.object.vo.relation.FavoriteNoteVO;
import com.ksw.service.forObject.object.FavoriteService;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FavoriteNoteService {
	
    @Autowired
    private FavoriteNoteMapper favoriteNoteMapper;
    @Autowired
    private FavoriteService favoriteService;
    

    @Transactional(readOnly = true)
    public List<Integer> findDislikedNoteNosByUserNo(Integer userNo) {
        return favoriteNoteMapper.findDislikedNoteNosByUserNo(userNo);
    }

    @Transactional
    public void addFavoriteNote(FavoriteNote favoriteNote) {
        favoriteNoteMapper.insert(favoriteNote);
    }

    @Transactional
    public void toggleFavorite(Integer userNo, Integer noteNo) {
        // 좋아요 상태 확인
        FavoriteNote favoriteNote = favoriteNoteMapper.findByUserNoAndNoteNo(userNo, noteNo);

        if (favoriteNote != null) {
            // 좋아요 데이터가 존재하면 삭제 요청
            scheduleDeleteFavorite(userNo, noteNo, favoriteNote.getFavoriteNo());
        } else {
            // 좋아요 데이터가 존재하지 않으면 추가 요청
            scheduleAddFavorite(userNo, noteNo);
        }
    }
 
    @Async
    public void scheduleAddFavorite(Integer userNo, Integer noteNo) {
        try {
            // 10초 대기
            TimeUnit.SECONDS.sleep(10);

            // 10초 후 좋아요 추가
            Favorite favorite = favoriteService.saveFavorite();
            FavoriteNote favoriteNote = new FavoriteNote();
            favoriteNote.setUserNo(userNo);
            favoriteNote.setNoteNo(noteNo);
            favoriteNote.setFavoriteNo(favorite.getFavoriteNo());
            favoriteNoteMapper.insert(favoriteNote);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Async
    public void scheduleDeleteFavorite(Integer userNo, Integer noteNo, Integer favoriteNo) {
        try {
            // 10초 대기
            TimeUnit.SECONDS.sleep(10);

            // 10초 후 좋아요 삭제
            favoriteNoteMapper.delete(userNo, noteNo);
            favoriteService.deleteFavorite(favoriteNo);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
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
