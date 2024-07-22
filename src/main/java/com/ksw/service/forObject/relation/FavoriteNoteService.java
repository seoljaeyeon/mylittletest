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
	
	public List<Map<String,Object>> getFavoriteListByUserNo(Integer userNo, Integer limit, Integer offset){
		return favoriteNoteMapper.getFavoriteListByUserNo(userNo, limit, offset);
	}
	
	public List<Integer> getCategoryListByUserNoAndFavoriteType(Integer userNo, Integer favoriteType){
		
		return favoriteNoteMapper.getCategoryListByUserNoAndFavoriteType(userNo, favoriteType);
	}
	
	public List<Map<String, Object>> getNoteListByUserNo(Integer userNo){
		List<Map<String,Object>> results = favoriteNoteMapper.getNoteListByUserNo(userNo);
		return results;
	}
	
	public Integer updateFavorite(
			Integer noteNo,
			Integer userNo,
			Integer requestType) {
		
		// DB로 최근 해당 요청과 같은 형태의 기록이 있는 지 먼저 확인
		FavoriteDTO favoriteDTO= favoriteNoteMapper.checkRecentFavoriteRequest(noteNo, userNo);
		Integer favoriteNo = null;
		if (favoriteDTO != null) {
			long currentTime = System.currentTimeMillis();
			long timeDifference = currentTime - favoriteDTO.getCreatedAt().getTime();
			favoriteNo = favoriteDTO.getFavoriteNo();
			if(favoriteNo!=null && timeDifference <= 5000) {
			// 있으면 시간 초 확인 해보고, 등록안하고, 에러코드 발행
				System.out.println("5초 내 기록 있음");
				return 100;
			};
		}
		
		// 없으면 해당 기록 찾아서 새로 업데이트 하고, 관계 테이블 등
		Favorite favorite = favoriteService.insert(favoriteNo, requestType);
		favoriteNoteMapper.insert(noteNo, userNo, favoriteNo);
		
		// 등록 후 변경된 타입 반환
		return favorite.getFavoriteType();
	}
	
	public Integer updateLessLook(
			Integer noteNo,
			Integer userNo,
			Integer requestType,
			Integer targetType) {
		
		FavoriteDTO favoriteDTO= favoriteNoteMapper.checkRecentFavoriteRequest(noteNo, userNo);
		Integer favoriteNo = null;
		if (favoriteDTO != null) {
			long currentTime = System.currentTimeMillis();
			long timeDifference = currentTime - favoriteDTO.getCreatedAt().getTime();
			favoriteNo = favoriteDTO.getFavoriteNo();
			if(favoriteNo!=null && timeDifference <= 5000) {
			// 있으면 시간 초 확인 해보고, 등록안하고, 에러코드 발행
				System.out.println("5초 내 기록 있음");
				return 100;
			};
		}
		
		// 없으면 해당 기록 찾아서 새로 업데이트 하고, 관계 테이블 등
		Favorite favorite = favoriteService.insert(favoriteNo, requestType);
		favoriteNoteMapper.insert(noteNo, userNo, favoriteNo);
		
		// 등록 후 변경된 타입 반환
		return favorite.getFavoriteType();
	}
	
	public Integer updateBlock(
			Integer noteNo,
			Integer userNo,
			Integer requestType,
			Integer targetType) {
		
		FavoriteDTO favoriteDTO= favoriteNoteMapper.checkRecentFavoriteRequest(noteNo, userNo);
		Integer favoriteNo = null;
		if (favoriteDTO != null) {
			long currentTime = System.currentTimeMillis();
			long timeDifference = currentTime - favoriteDTO.getCreatedAt().getTime();
			favoriteNo = favoriteDTO.getFavoriteNo();
			if(favoriteNo!=null && timeDifference <= 5000) {
			// 있으면 시간 초 확인 해보고, 등록안하고, 에러코드 발행
				System.out.println("5초 내 기록 있음");
				return 100;
			};
		}

		// 없으면 해당 기록 찾아서 새로 업데이트 하고, 관계 테이블 등
		Favorite favorite = favoriteService.insert(favoriteNo, requestType);
		favoriteNoteMapper.insert(noteNo, userNo, favoriteNo);
		
		// 등록 후 변경된 타입 반환
		return favorite.getFavoriteType();
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
