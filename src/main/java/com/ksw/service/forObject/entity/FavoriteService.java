package com.ksw.service.forObject.entity;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dao.forObject.entity.FavoriteRepository;
import com.ksw.dto.forObject.entity.FavoriteDTO;
import com.ksw.object.entity.Favorite;
import com.ksw.vo.forObject.entity.FavoriteVO;

@Service
public class FavoriteService {
	
	@Autowired
	private FavoriteRepository favoriteRepository;
	
	public Integer insert(Integer favoriteNo, Integer requestType) {
		Favorite favorite = new Favorite();
		
		// 등록된 적이 있다면 null 반환
	    if (favoriteNo != null) {
	        favorite.setFavoriteNo(favoriteNo); // 기존 엔티티 업데이트
	        favorite.setFavoriteType(requestType); 
	        favoriteRepository.save(favorite); 
	        return null;
	    }
	    favorite.setFavoriteType(requestType);
		favorite = favoriteRepository.save(favorite);
		return favorite.getFavoriteNo();
	}
	
    // Entity -> DTO 변환 메소드
    public FavoriteDTO convertToDTO(Favorite favorite) {
    	FavoriteDTO dto = new FavoriteDTO();
    	if(favorite==null) {
    		System.out.println("Favorite to FavoriteDTO failed. Empty FavoriteDTO created. Favorite is null");
    		return dto;
    	}
    	
    	dto.setCreatedAt(favorite.getCreatedAt());
    	dto.setFavoriteNo(favorite.getFavoriteNo());
    	dto.setFavoriteType(favorite.getFavoriteType());
        return dto;
    }

    // DTO -> VO 변환 메소드
    public FavoriteVO convertToVO(FavoriteDTO favoriteDTO) {
    	if (favoriteDTO == null) {
    		System.out.println("FavoriteDTO to FavoriteVO failed. Empty FavoriteVO created. FavoriteDTO is null");
    		return new FavoriteVO.Builder().build();
    	}
        return new FavoriteVO.Builder()
        		.favoriteType(favoriteDTO.getFavoriteType())
                .favoriteNo(favoriteDTO.getFavoriteNo())
                .createdAt(favoriteDTO.getCreatedAt())
                .build();
    }
}
