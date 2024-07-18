package com.ksw.service.forObject.relation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dao.forObject.relation.FavoriteCategoryMapper;
import com.ksw.dto.forObject.relation.FavoriteCategoryDTO;
import com.ksw.object.entity.Favorite;
import com.ksw.object.relation.FavoriteCategory;
import com.ksw.service.forObject.entity.CategoryService;
import com.ksw.service.forObject.entity.FavoriteService;
import com.ksw.service.forObject.entity.UserService;
import com.ksw.vo.forObject.relation.FavoriteCategoryVO;

@Service
public class FavoriteCategoryService {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private FavoriteService favoriteService;
	@Autowired
	private UserService userService;
	@Autowired
	private FavoriteCategoryMapper favoriteCategoryMapper;
	
	
	public Integer updateBookmark(
			Integer categoryNo,
			Integer userNo,
			Integer requestType) {
		
		// DB로 최근 해당 요청이 있는 지 먼저 확인
		Integer favoriteNo = favoriteCategoryMapper.checkRecentFavoriteRequest(categoryNo, userNo);
		
		// 있으면 등록안하고, 에러코드 발행
		if(favoriteNo!=null) {
			System.out.println("5초 내 기록 있음");
			return 100;
		};
		
		// 없으면 해당 기록 찾아서 새로 업데이트 하고, 관계 테이블 등
		Favorite favorite = favoriteService.insert(favoriteNo, requestType);
		favoriteCategoryMapper.insert(categoryNo, userNo, favoriteNo);
		
		// 등록 후 변경된 타입 반환
		return favorite.getFavoriteType();
	}
	
    // Entity -> DTO 변환 메소드
    public FavoriteCategoryDTO convertToDTO(FavoriteCategory favoriteCategoryEntity) {
    	FavoriteCategoryDTO dto = new FavoriteCategoryDTO();
    	if (favoriteCategoryEntity == null) {
    		System.out.println("FavoriteCategory to FavoriteCategoryDTO failed. Empty FavoriteCategoryDTO created. FavoriteCategory is null");
    		return dto;
    	}
    	dto.setCategoryDTO(categoryService.convertToDTO(favoriteCategoryEntity.getCategory()));
    	dto.setFavoriteDTO(favoriteService.convertToDTO(favoriteCategoryEntity.getFavorite()));
    	dto.setUserDTO(userService.convertToDTO(favoriteCategoryEntity.getUser()));
        return dto;
    }

    // DTO -> VO 변환 메소드
    public FavoriteCategoryVO convertToVO(FavoriteCategoryDTO favoriteCategoryDTO) {
    	if (favoriteCategoryDTO == null) {
    		System.out.println("FavoriteCategoryDTO to FavoriteCategoryVO failed. Empty FavoriteCategoryVO created. FavoriteCategoryDTO is null");    		
    		return new FavoriteCategoryVO.Builder().build();
    	}
        return new FavoriteCategoryVO.Builder()
                .userVO(userService.convertToVO(favoriteCategoryDTO.getUserDTO()))
                .categoryVO(categoryService.convertToVO(favoriteCategoryDTO.getCategoryDTO()))
                .favoriteVO(favoriteService.convertToVO(favoriteCategoryDTO.getFavoriteDTO()))
                .build();
    }
}
