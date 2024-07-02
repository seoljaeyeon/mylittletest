package com.ksw.service.object;

import com.ksw.dto.forObject.ReportManagementDTO;
import com.ksw.object.entity.mybatis.ReportManagement;
import com.ksw.object.vo.ReportManagementVO;
import org.springframework.stereotype.Service;

@Service
public class ReportManagementService {

    // Entity -> DTO 변환 메소드
    public ReportManagementDTO convertToDTO(ReportManagement reportManagementEntity) {
        return new ReportManagementDTO.Builder()
                .userNo(reportManagementEntity.getUserNo())
                .managerNo(reportManagementEntity.getManagerNo())
                .solverNo(reportManagementEntity.getSolverNo())
                .build();
    }

    // DTO -> VO 변환 메소드
    public ReportManagementVO convertToVO(ReportManagementDTO reportManagementDTO) {
        return new ReportManagementVO.Builder()
                .userNo(reportManagementDTO.getUserNo())
                .managerNo(reportManagementDTO.getManagerNo())
                .solverNo(reportManagementDTO.getSolverNo())
                .build();
    }
}
