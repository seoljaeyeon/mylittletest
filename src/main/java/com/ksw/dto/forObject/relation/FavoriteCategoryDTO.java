package com.ksw.dto.forObject.relation;

import java.io.Serializable;

import com.ksw.dto.forObject.entity.CategoryDTO;
import com.ksw.dto.forObject.entity.FavoriteDTO;
import com.ksw.dto.forObject.entity.UserDTO;

public class FavoriteCategoryDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	

    private UserDTO userDTO;
    private CategoryDTO categoryDTO;
    private FavoriteDTO favoriteDTO;

    // 기본 생성자
    public FavoriteCategoryDTO() {}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public CategoryDTO getCategoryDTO() {
		return categoryDTO;
	}

	public void setCategoryDTO(CategoryDTO categoryDTO) {
		this.categoryDTO = categoryDTO;
	}

	public FavoriteDTO getFavoriteDTO() {
		return favoriteDTO;
	}

	public void setFavoriteDTO(FavoriteDTO favoriteDTO) {
		this.favoriteDTO = favoriteDTO;
	}

    
}
