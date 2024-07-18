package com.ksw.service.forObject.relation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dao.forObject.relation.FavoriteReplyMapper;
import com.ksw.dto.forObject.relation.FavoriteReplyDTO;
import com.ksw.object.entity.Favorite;
import com.ksw.object.relation.FavoriteReply;
import com.ksw.service.forObject.entity.FavoriteService;
import com.ksw.service.forObject.entity.ReplyService;
import com.ksw.service.forObject.entity.UserService;
import com.ksw.vo.forObject.relation.FavoriteReplyVO;

@Service
public class FavoriteReplyService {

	@Autowired
	private FavoriteService favoriteService;
	@Autowired
	private ReplyService replyService;
	@Autowired
	private UserService userService;
	@Autowired
	private FavoriteReplyMapper favoriteReplyMapper;
	
	public Integer updateFavorite(
			Integer replyNo,
			Integer userNo,
			Integer requestType) {
		
		// DB로 최근 해당 요청이 있는 지 먼저 확인
		Integer favoriteNo = favoriteReplyMapper.checkRecentFavoriteRequest(replyNo, userNo);
		
		// 있으면 등록안하고, 에러코드 발행
		if(favoriteNo!=null) {
			System.out.println("5초 내 기록 있음");
			return 100;
		};
		
		// 없으면 해당 기록 찾아서 새로 업데이트 하고, 관계 테이블 등
		Favorite favorite = favoriteService.insert(favoriteNo, requestType);
		favoriteReplyMapper.insert(replyNo, userNo, favoriteNo);
		
		// 등록 후 변경된 타입 반환
		return favorite.getFavoriteType();
	}
	
	public Integer updateLessLook(
			Integer replyNo,
			Integer userNo,
			Integer requestType,
			Integer targetType) {
		
		// DB로 최근 해당 요청이 있는 지 먼저 확인
		Integer favoriteNo = favoriteReplyMapper.checkRecentFavoriteRequest(replyNo, userNo);
		
		// 있으면 등록안하고, 에러코드 발행
		if(favoriteNo!=null) {
			System.out.println("5초 내 기록 있음");
			return 100;
		};
		
		// 없으면 해당 기록 찾아서 새로 업데이트 하고, 관계 테이블 등
		Favorite favorite = favoriteService.insert(favoriteNo, requestType);
		favoriteReplyMapper.insert(replyNo, userNo, favoriteNo);
		
		// 등록 후 변경된 타입 반환
		return favorite.getFavoriteType();
	}
	
	public Integer updateBlock(
			Integer replyNo,
			Integer userNo,
			Integer requestType,
			Integer targetType) {
		
		// DB로 최근 해당 요청이 있는 지 먼저 확인
		Integer favoriteNo = favoriteReplyMapper.checkRecentFavoriteRequest(replyNo, userNo);
		
		// 있으면 등록안하고, 에러코드 발행
		if(favoriteNo!=null) {
			System.out.println("5초 내 기록 있음");
			return 100;
		};
		
		// 없으면 해당 기록 찾아서 새로 업데이트 하고, 관계 테이블 등
		Favorite favorite = favoriteService.insert(favoriteNo, requestType);
		favoriteReplyMapper.insert(replyNo, userNo, favoriteNo);
		
		// 등록 후 변경된 타입 반환
		return favorite.getFavoriteType();
	}

	
    // Entity -> DTO 변환 메소드
    public FavoriteReplyDTO convertToDTO(FavoriteReply favoriteReplyEntity) {
    	FavoriteReplyDTO dto = new FavoriteReplyDTO();
    	if (favoriteReplyEntity == null) {
    		System.out.println("FavoriteReply to FavoriteReplyDTO failed. Empty FavoriteReplyDTO created. FavoriteReply is null");    		
    		return dto;
    	}
    	dto.setFavoriteDTO(favoriteService.convertToDTO(favoriteReplyEntity.getFavorite()));
    	dto.setReplyDTO(replyService.convertToDTO(favoriteReplyEntity.getReply()));
    	dto.setUserDTO(userService.convertToDTO(favoriteReplyEntity.getUser()));
        return dto;    
    }

    // DTO -> VO 변환 메소드
    public FavoriteReplyVO convertToVO(FavoriteReplyDTO favoriteReplyDTO) {
    	if (favoriteReplyDTO == null) {
    		System.out.println("FavoriteReplyDTO to FavoriteReplyVO failed. Empty FavoriteReplyVO created. FavoriteReplyDTO is null");    		
    		return new FavoriteReplyVO.Builder().build();
    	}
        return new FavoriteReplyVO.Builder()
                .userVO(userService.convertToVO(favoriteReplyDTO.getUserDTO()))
                .replyVO(replyService.convertToVO(favoriteReplyDTO.getReplyDTO()))
                .favoriteVO(favoriteService.convertToVO(favoriteReplyDTO.getFavoriteDTO()))
                .build();
    }
}
