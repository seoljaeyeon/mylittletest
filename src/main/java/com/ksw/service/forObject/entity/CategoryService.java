package com.ksw.service.forObject.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ksw.dao.forObject.entity.CategoryRepository;
import com.ksw.dto.forObject.entity.CategoryDTO;
import com.ksw.object.entity.Category;
import com.ksw.vo.forObject.entity.CategoryVO;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional
    public Category saveCategory(Category category) {
        if (categoryRepository.existsByCategoryTitle(category.getCategoryTitle())) {
        	System.out.print("카테고리번호"+category.getCategoryNo());
            return categoryRepository.findByCategoryTitle(category.getCategoryTitle());
        }
        return categoryRepository.save(category);
    }
	
    // Entity -> DTO 변환 메소드
    public CategoryDTO convertToDTO(Category category) {
    	
    	CategoryDTO dto = new CategoryDTO();
    	
    	if (category == null) {
    		System.out.println("Category to CategoryDTO failed. Empty CategoryDTO created. Category is null");
    		return dto;
    	}
    	dto.setCategoryNo(category.getCategoryNo());
    	dto.setCategoryTitle(category.getCategoryTitle());
    	dto.setCreatedAt(category.getCreatedAt());
    	dto.setIsActive(category.getIsActive());
        return dto;
    }
    
    public Category convertToEntity(CategoryDTO categoryDTO) {
    	Category category = new Category();
    	if (categoryDTO == null) {
    		System.out.println("CategoryDTO to Category failed. Empty Category created. CategoryDTO is null");
    		return category;
    	}
        category.setCategoryNo(categoryDTO.getCategoryNo());
        category.setCategoryTitle(categoryDTO.getCategoryTitle());
        category.setIsActive(categoryDTO.getIsActive());
        category.setCreatedAt(categoryDTO.getCreatedAt());
        return category;
    }

    // DTO -> VO 변환 메소드
    public CategoryVO convertToVO(CategoryDTO categoryDTO) {
    	if(categoryDTO == null) {
    		System.out.println("CategoryDTO to CategoryVO failed. Empty CategoryVO created. CategoryDTO is null");
    		return new CategoryVO.Builder().build();
    	}
    	
        return new CategoryVO.Builder()
                .categoryNo(categoryDTO.getCategoryNo())
                .categoryTitle(categoryDTO.getCategoryTitle())
                .isActive(categoryDTO.getIsActive())
                .createdAt(categoryDTO.getCreatedAt())
                .build();
    }
}
