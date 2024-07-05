package com.ksw.service.forObject.object;

import com.ksw.dao.object.CategoryRepository;
import com.ksw.dto.forObject.object.CategoryDTO;
import com.ksw.object.entity.jpa.Category;
import com.ksw.object.entity.jpa.Note;
import com.ksw.object.vo.object.CategoryVO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
	
    // Entity -> DTO 변환 메소드
    public CategoryDTO convertToDTO(Category category) {
        return new CategoryDTO.Builder()
                .categoryNo(category.getCategoryNo())
                .categoryTitle(category.getCategoryTitle())
                .isActive(category.getIsActive())
                .createdAt(category.getCreatedAt())
                .build();
    }
    
    public Category convertToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryNo(categoryDTO.getCategoryNo());
        category.setCategoryTitle(categoryDTO.getCategoryTitle());
        category.setIsActive(categoryDTO.getIsActive());
        category.setCreatedAt(categoryDTO.getCreatedAt());
        return category;
    }

    // DTO -> VO 변환 메소드
    public CategoryVO convertToVO(CategoryDTO categoryDTO) {
        return new CategoryVO.Builder()
                .categoryNo(categoryDTO.getCategoryNo())
                .categoryTitle(categoryDTO.getCategoryTitle())
                .isActive(categoryDTO.getIsActive())
                .createdAt(categoryDTO.getCreatedAt())
                .build();
    }
}
