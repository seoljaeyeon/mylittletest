package com.ksw.service.forObject.relation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dto.forObject.relation.CategoryViewDTO;
import com.ksw.object.relation.CategoryView;
import com.ksw.service.forObject.entity.CategoryService;
import com.ksw.service.forObject.entity.UserService;
import com.ksw.service.forObject.entity.ViewService;
import com.ksw.vo.forObject.relation.CategoryViewVO;

@Service
public class CategoryViewService {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ViewService viewService;
	@Autowired
	private UserService userService;
	
	public CategoryViewDTO convertToDTO(CategoryView categoryView) {
		CategoryViewDTO dto = new CategoryViewDTO(
				categoryService.convertToDTO(categoryView.getCategory()),
				viewService.convertToDTO(categoryView.getView()),
				userService.convertToDTO(categoryView.getUser()));
		return dto; 
	}
	
	public CategoryViewVO convertToVO(CategoryViewDTO dto) {
		return new CategoryViewVO.Builder()
				.categoryVO(categoryService.convertToVO(dto.getCategoryDTO()))
				.viewVO(viewService.convertToVO(dto.getViewDTO()))
				.userVO(userService.convertToVO(dto.getUserDTO()))
				.build();
	}
	
}
