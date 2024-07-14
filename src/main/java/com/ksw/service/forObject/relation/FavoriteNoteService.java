package com.ksw.service.forObject.relation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dto.forObject.relation.FavoriteNoteDTO;
import com.ksw.object.relation.FavoriteNote;
import com.ksw.service.forObject.entity.FavoriteService;
import com.ksw.service.forObject.entity.NoteService;
import com.ksw.service.forObject.entity.UserService;
import com.ksw.vo.forObject.relation.FavoriteNoteVO;

@Service
public class FavoriteNoteService {

	@Autowired
	private FavoriteService favoriteService;
	@Autowired
	private NoteService noteService;
	@Autowired
	private UserService userService;
	
	
    // Entity -> DTO 변환 메소드
    public FavoriteNoteDTO convertToDTO(FavoriteNote favoriteNoteEntity) {
    	FavoriteNoteDTO dto = new FavoriteNoteDTO();
    	if (favoriteNoteEntity == null) {
    		System.out.println("FavoriteNote to FavoriteNoteDTO failed. Empty FavoriteNoteDTO created. FavoriteNote is null");    		
    		return dto;
    	}
    	dto.setFavoriteDTO(favoriteService.convertToDTO(favoriteNoteEntity.getFavorite()));
    	dto.setNoteDTO(noteService.convertToDTO(favoriteNoteEntity.getNote()));
    	dto.setUserDTO(userService.convertToDTO(favoriteNoteEntity.getUser()));
        return dto;    
    }

    // DTO -> VO 변환 메소드
    public FavoriteNoteVO convertToVO(FavoriteNoteDTO favoriteNoteDTO) {
    	if (favoriteNoteDTO == null) {
    		System.out.println("FavoriteNoteDTO to FavoriteNoteVO failed. Empty FavoriteNoteVO created. FavoriteNoteDTO is null");    		
    		return new FavoriteNoteVO.Builder().build();
    	}
        return new FavoriteNoteVO.Builder()
                .userVO(userService.convertToVO(favoriteNoteDTO.getUserDTO()))
                .noteVO(noteService.convertToVO(favoriteNoteDTO.getNoteDTO()))
                .favoriteVO(favoriteService.convertToVO(favoriteNoteDTO.getFavoriteDTO()))
                .build();
    }
}
