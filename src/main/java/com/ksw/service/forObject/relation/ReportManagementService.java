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
    	dto.setManagerDTO(userService.convertToDTO(reportManagementEntity.getManager()));
    	dto.setSolverDTO(userService.convertToDTO(reportManagementEntity.getSolver()));
    	dto.setUserDTO(userService.convertToDTO(reportManagementEntity.getUser()));
        return dto;
        
    }

    // DTO -> VO 변환 메소드
    public ReportManagementVO convertToVO(ReportManagementDTO reportManagementDTO) {
        return new ReportManagementVO.Builder()
                .userVO(userService.convertToVO(reportManagementDTO.getUserDTO()))
                .managerVO(userService.convertToVO(reportManagementDTO.getManagerDTO()))
                .solverVO(userService.convertToVO(reportManagementDTO.getSolverDTO()))
                .build();
    }
}
