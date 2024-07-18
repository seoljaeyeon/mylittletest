package com.ksw.dto.forObject.relation;

import java.io.Serializable;

import com.ksw.dto.forObject.entity.CategoryDTO;
import com.ksw.dto.forObject.entity.UserDTO;

public class CategoryUserDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	

    private CategoryDTO categoryDTO;
    private UserDTO userDTO;

    // 기본 생성자
    public CategoryUserDTO() {}
    
    public CategoryUserDTO(UserDTO userDTO, CategoryDTO categoryDTO) {
    	super();
    	this.categoryDTO = categoryDTO;
    	this.userDTO = userDTO;
    }

	public CategoryDTO getCategoryDTO() {
		return categoryDTO;
	}

	public void setCategoryDTO(CategoryDTO categoryDTO) {
		this.categoryDTO = categoryDTO;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
}
