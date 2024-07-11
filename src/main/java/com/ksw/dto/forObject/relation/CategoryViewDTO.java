package com.ksw.dto.forObject.relation;

import com.ksw.dto.forObject.entity.CategoryDTO;
import com.ksw.dto.forObject.entity.ViewDTO;

public class CategoryViewDTO {
    
    private CategoryDTO categoryDTO;
    private ViewDTO viewDTO;
    
    // 기본 생성자
    public CategoryViewDTO() {}
    
	public CategoryDTO getCategoryDTO() {
		return categoryDTO;
	}
	public void setCategoryDTO(CategoryDTO categoryDTO) {
		this.categoryDTO = categoryDTO;
	}
	public ViewDTO getViewDTO() {
		return viewDTO;
	}
	public void setViewDTO(ViewDTO viewDTO) {
		this.viewDTO = viewDTO;
	}


}
