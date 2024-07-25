package com.ksw.service.forObject.relation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dao.forObject.relation.FavoriteNoteMapper;
import com.ksw.dto.forObject.entity.FavoriteDTO;
import com.ksw.dto.forObject.relation.FavoriteNoteDTO;
import com.ksw.object.entity.Favorite;
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
	@Autowired
	private FavoriteNoteMapper favoriteNoteMapper;
	
	public Integer countFavoriteByNoteNo(Integer noteNo) {
		Integer result = favoriteNoteMapper.countFavoriteByNoteNo(noteNo);
		return result;
	}
	
	public List<Map<String,Object>> getFavoriteListByUserNo(Integer userNo, Integer limit, Integer offset){
		return favoriteNoteMapper.getFavoriteListByUserNo(userNo, limit, offset);
	}
	
	public List<Map<String,Object>> getFavoriteSimilarListByUserNo(Integer userNo, Integer limit, Integer offset, String searchInput){
		return favoriteNoteMapper.getFavoriteSimilarListByUserNo(userNo, limit, offset, searchInput);
	}
	
	public List<Map<String, Object>> getCategoryListByUserNoAndFavoriteType(Integer userNo, Integer favoriteType){
		return favoriteNoteMapper.getCategoryListByUserNoAndFavoriteType(userNo, favoriteType);
	}
	
	public List<Map<String, Object>> getSimilarCategoryListByUserNoAndFavoriteType(Integer userNo, Integer favoriteType, String searchInput){
		return favoriteNoteMapper.getSimilarCategoryListByUserNoAndFavoriteType(userNo, favoriteType, searchInput);
	}
	
	public List<Map<String, Object>> getFavoritedNoteListByUserNo(Integer userNo, String sort, Integer limit, Integer offset, Integer searchType, String searchInput){
		List<Map<String,Object>> results = favoriteNoteMapper.getFavoritedNoteListByUserNo(userNo, sort, limit, offset, searchType, searchInput);
		return results;
	}
	
	public Integer updateFavorite(
			Integer noteNo,
			Integer userNo,
			Integer requestType,
			Integer targetType) {
		
		// DB로 최근 해당 요청이 있는 지 먼저 확인
		Integer favoriteNo = favoriteNoteMapper.checkRecentFavoriteRequest(noteNo, userNo);
		// 있으면 등록안하고, 에러코드 발행
		if(favoriteNo!=null) {
			System.out.println("5초 내 기록 있음");
			return 100;
		};
		
		favoriteNo = favoriteNoteMapper.getFavoriteNo(noteNo, userNo);
		// 없으면 해당 기록 찾아서 새로 업데이트 하고, 관계 테이블 등록
		Integer newFavoriteNo = favoriteService.insert(favoriteNo, requestType);
		if (newFavoriteNo != null) {
			favoriteNoteMapper.insert(noteNo, userNo, newFavoriteNo);
		} else {
			favoriteNoteMapper.update(noteNo, userNo, favoriteNo);
		}
		
		// 등록 후 변경된 타입 반환
		return requestType;
	}
	
	 // 차단 상태 확인
    public boolean isBlocked(Integer userNo, Integer noteNo) {
        return favoriteNoteMapper.isBlocked(userNo, noteNo);
    }
	
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
