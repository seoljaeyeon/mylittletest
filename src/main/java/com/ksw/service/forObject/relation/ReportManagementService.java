package com.ksw.service.forObject.relation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dto.forObject.relation.ReportManagementDTO;
import com.ksw.object.relation.ReportManagement;
import com.ksw.service.forObject.entity.UserService;
import com.ksw.vo.forObject.relation.ReportManagementVO;

@Service
public class ReportManagementService {

	@Autowired
	private UserService userService;
	
    // Entity -> DTO 변환 메소드
    public ReportManagementDTO convertToDTO(ReportManagement reportManagementEntity) {
    	ReportManagementDTO dto = new ReportManagementDTO();
    	if(reportManagementEntity == null) {
    		System.out.println("ReportManagement to ReportManagementDTO failed. Empty ReportManagementDTO created. ReportManagement is null");   	
    		return dto;
    	}
    	dto.setManagerDTO(userService.convertToDTO(reportManagementEntity.getManager()));
    	dto.setSolverDTO(userService.convertToDTO(reportManagementEntity.getSolver()));
    	dto.setUserDTO(userService.convertToDTO(reportManagementEntity.getUser()));
        return dto;
        
    }

    // DTO -> VO 변환 메소드
    public ReportManagementVO convertToVO(ReportManagementDTO reportManagementDTO) {
    	if (reportManagementDTO == null) {
    		System.out.println("ReportManagementDTO to ReportManagementVO failed. Empty ReportManagementVO created. ReportManagementDTO is null");   	
    		return new ReportManagementVO.Builder().build();
    	}
        return new ReportManagementVO.Builder()
                .userVO(userService.convertToVO(reportManagementDTO.getUserDTO()))
                .managerVO(userService.convertToVO(reportManagementDTO.getManagerDTO()))
                .solverVO(userService.convertToVO(reportManagementDTO.getSolverDTO()))
                .build();
    }
}
