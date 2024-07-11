package com.ksw.service.forObject.relation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dto.forObject.relation.FavoriteCategoryDTO;
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
	
	
    // Entity -> DTO 변환 메소드
    public FavoriteCategoryDTO convertToDTO(FavoriteCategory favoriteCategoryEntity) {
    	FavoriteCategoryDTO dto = new FavoriteCategoryDTO();
    	dto.setCategoryDTO(categoryService.convertToDTO(favoriteCategoryEntity.getCategory()));
    	dto.setFavoriteDTO(favoriteService.convertToDTO(favoriteCategoryEntity.getFavorite()));
    	dto.setUserDTO(userService.convertToDTO(favoriteCategoryEntity.getUser()));
        return dto;
    }

    // DTO -> VO 변환 메소드
    public FavoriteCategoryVO convertToVO(FavoriteCategoryDTO favoriteCategoryDTO) {
        return new FavoriteCategoryVO.Builder()
                .userVO(userService.convertToVO(favoriteCategoryDTO.getUserDTO()))
                .categoryVO(categoryService.convertToVO(favoriteCategoryDTO.getCategoryDTO()))
                .favoriteVO(favoriteService.convertToVO(favoriteCategoryDTO.getFavoriteDTO()))
                .build();
    }
}
