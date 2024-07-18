package com.ksw.service.forObject.relation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dao.forObject.relation.CategoryUserMapper;
import com.ksw.dto.forObject.relation.CategoryUserDTO;
import com.ksw.object.entity.Category;
import com.ksw.object.entity.User;
import com.ksw.object.relation.CategoryUser;
import com.ksw.service.forObject.entity.CategoryService;
import com.ksw.service.forObject.entity.UserService;
import com.ksw.vo.forObject.relation.CategoryUserVO;

@Service
public class CategoryUserService {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryUserMapper categoryUserMapper;
	
	
    // Entity -> DTO 변환 메소드
    public CategoryUserDTO convertToDTO(CategoryUser categoryUserEntity) {
    	CategoryUserDTO dto = new CategoryUserDTO();
    	if (categoryUserEntity == null) {
    		System.out.println("CategoryUser to CategoryUserDTO failed. Empty CategoryUserDTO created. CategoryUser is null");    		
    		return dto;
    	}
    	dto.setCategoryDTO(categoryService.convertToDTO(categoryUserEntity.getCategory()));
    	dto.setUserDTO(userService.convertToDTO(categoryUserEntity.getUser()));
        return dto;
    }

    // DTO -> VO 변환 메소드
    public CategoryUserVO convertToVO(CategoryUserDTO categoryUserDTO) {
    	if (categoryUserDTO == null) {
    		System.out.println("CategoryUserDTO to CategoryUserVO failed. Empty CategoryUserVO created. CategoryUserDTO is null");    		
    		return new CategoryUserVO.Builder().build();
    	}
        return new CategoryUserVO.Builder()
                .categoryVO(categoryService.convertToVO(categoryUserDTO.getCategoryDTO()))
                .userVO(userService.convertToVO(categoryUserDTO.getUserDTO()))
                .build();
    }
    
    // DTO -> Entity 변환 메소드
    public CategoryUser convertToEntity(CategoryUserDTO categoryUserDTO) {
        CategoryUser categoryUserEntity = new CategoryUser();
        if(categoryUserDTO == null) {
    		System.out.println("CategoryUserDTO to CategoryUser failed. Empty CategoryUser created. CategoryUserDTO is null");    		
        	return categoryUserEntity;
        }
        Category categoryEntity = categoryService.convertToEntity(categoryUserDTO.getCategoryDTO());
        User userEntity = userService.convertToEntity(categoryUserDTO.getUserDTO());

        categoryUserEntity.setCategory(categoryEntity);
        categoryUserEntity.setUser(userEntity);
        return categoryUserEntity;
    }
}
