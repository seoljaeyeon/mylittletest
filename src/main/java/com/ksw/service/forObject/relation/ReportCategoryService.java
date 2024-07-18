package com.ksw.service.forObject.relation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dto.forObject.relation.ReportCategoryDTO;
import com.ksw.object.relation.ReportCategory;
import com.ksw.service.forObject.entity.CategoryService;
import com.ksw.service.forObject.entity.ReportService;
import com.ksw.service.forObject.entity.UserService;
import com.ksw.vo.forObject.relation.ReportCategoryVO;

@Service
public class ReportCategoryService {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UserService userService;
	@Autowired
	private ReportService ReportService;
	
    // Entity -> DTO 변환 메소드
    public ReportCategoryDTO convertToDTO(ReportCategory reportCategoryEntity) {
    	ReportCategoryDTO dto = new ReportCategoryDTO();
    	if (reportCategoryEntity == null) {
    		System.out.println("ReportCategoryDTO to ReportCategory failed. Empty ReportCategory created. ReportCategoryDTO is null");   	
    		return dto;
    	}
    	dto.setCategoryDTO(categoryService.convertToDTO(reportCategoryEntity.getCategory()));
    	dto.setReportDTO(ReportService.convertToDTO(reportCategoryEntity.getReport()));
    	dto.setUserDTO(userService.convertToDTO(reportCategoryEntity.getUser()));
        return dto;
    }

    // DTO -> VO 변환 메소드
    public ReportCategoryVO convertToVO(ReportCategoryDTO reportCategoryDTO) {
    	if (reportCategoryDTO == null) {
    		System.out.println("ReportCategoryDTO to ReportCategoryVO failed. Empty ReportCategoryVO created. ReportCategoryDTO is null");   	
    		return new ReportCategoryVO.Builder().build();
    	}
        return new ReportCategoryVO.Builder()
                .userVO(userService.convertToVO(reportCategoryDTO.getUserDTO()))
                .categoryVO(categoryService.convertToVO(reportCategoryDTO.getCategoryDTO()))
                .reportVO(ReportService.convertToVO(reportCategoryDTO.getReportDTO()))
                .build();
    }
}
