package com.ksw.service.forObject.entity;

import org.springframework.stereotype.Service;

import com.ksw.dto.forObject.entity.CategoryDTO;
import com.ksw.object.entity.Category;
import com.ksw.vo.forObject.entity.CategoryVO;

@Service
public class CategoryService {

    // Entity -> DTO 변환 메소드
    public CategoryDTO convertToDTO(Category category) {
    	if (category == null) {
    		return null;
    	}
    	
        return new CategoryDTO.Builder()
                .categoryNo(category.getCategoryNo())
                .categoryTitle(category.getCategoryTitle())
                .isActive(category.getIsActive())
                .createdAt(category.getCreatedAt())
                .build();
    }
    
    public Category convertToEntity(CategoryDTO categoryDTO) {
    	if (categoryDTO == null) {
    		return null;
    	}
        Category category = new Category();
        category.setCategoryNo(categoryDTO.getCategoryNo());
        category.setCategoryTitle(categoryDTO.getCategoryTitle());
        category.setIsActive(categoryDTO.getIsActive());
        category.setCreatedAt(categoryDTO.getCreatedAt());
        return category;
    }

    // DTO -> VO 변환 메소드
    public CategoryVO convertToVO(CategoryDTO categoryDTO) {
    	if(categoryDTO == null) {
    		return null;
    	}
    	
        return new CategoryVO.Builder()
                .categoryNo(categoryDTO.getCategoryNo())
                .categoryTitle(categoryDTO.getCategoryTitle())
                .isActive(categoryDTO.getIsActive())
                .createdAt(categoryDTO.getCreatedAt())
                .build();
    }
}
