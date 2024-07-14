package com.ksw.dto.forObject.relation;

import java.io.Serializable;

import com.ksw.dto.forObject.entity.CategoryDTO;
import com.ksw.dto.forObject.entity.UserDTO;
import com.ksw.dto.forObject.entity.ViewDTO;

public class CategoryViewDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
    
    private CategoryDTO categoryDTO;
    private ViewDTO viewDTO;
    private UserDTO userDTO;
    
    // 기본 생성자
    public CategoryViewDTO() {}
    
    // 기본 생성자
    public CategoryViewDTO(CategoryDTO categoryDTO, ViewDTO viewDTO, UserDTO userDTO) {
    	super();
    	this.categoryDTO = categoryDTO;
    	this.viewDTO = viewDTO;
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

	public ViewDTO getViewDTO() {
		return viewDTO;
	}
	public void setViewDTO(ViewDTO viewDTO) {
		this.viewDTO = viewDTO;
	}


}
