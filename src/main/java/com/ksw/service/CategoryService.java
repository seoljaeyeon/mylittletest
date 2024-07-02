package com.ksw.service;

import com.ksw.dto.forObject.CategoryDTO;
import com.ksw.object.entity.jpa.Category;
import com.ksw.object.vo.CategoryVO;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    // Entity -> DTO 변환 메소드
    public CategoryDTO convertToDTO(Category category) {
        return new CategoryDTO.Builder()
                .categoryNo(category.getCategoryNo())
                .categoryTitle(category.getCategoryTitle())
                .isActive(category.getIsActive())
                .createdAt(category.getCreatedAt())
                .build();
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
