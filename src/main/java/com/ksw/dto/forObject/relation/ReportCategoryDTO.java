package com.ksw.dto.forObject.relation;

import com.ksw.dto.forObject.entity.CategoryDTO;
import com.ksw.dto.forObject.entity.ReportDTO;
import com.ksw.dto.forObject.entity.UserDTO;

public class ReportCategoryDTO {

    private UserDTO userDTO;
    private CategoryDTO categoryDTO;
    private ReportDTO reportDTO;

    // 기본 생성자
    public ReportCategoryDTO() {}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public CategoryDTO getCategoryDTO() {
		return categoryDTO;
	}

	public void setCategoryDTO(CategoryDTO categoryDTO) {
		this.categoryDTO = categoryDTO;
	}

	public ReportDTO getReportDTO() {
		return reportDTO;
	}

	public void setReportDTO(ReportDTO reportDTO) {
		this.reportDTO = reportDTO;
	}

}
