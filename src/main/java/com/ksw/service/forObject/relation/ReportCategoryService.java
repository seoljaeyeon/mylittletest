package com.ksw.service.forObject.relation;

import com.ksw.dto.forObject.relation.ReportCategoryDTO;
import com.ksw.object.entity.mybatis.ReportCategory;
import com.ksw.object.vo.relation.ReportCategoryVO;

import org.springframework.stereotype.Service;

@Service
public class ReportCategoryService {

    // Entity -> DTO 변환 메소드
    public ReportCategoryDTO convertToDTO(ReportCategory reportCategoryEntity) {
        return new ReportCategoryDTO.Builder()
                .userNo(reportCategoryEntity.getUserNo())
                .categoryNo(reportCategoryEntity.getCategoryNo())
                .reportNo(reportCategoryEntity.getReportNo())
                .build();
    }

    // DTO -> VO 변환 메소드
    public ReportCategoryVO convertToVO(ReportCategoryDTO reportCategoryDTO) {
        return new ReportCategoryVO.Builder()
                .userNo(reportCategoryDTO.getUserNo())
                .categoryNo(reportCategoryDTO.getCategoryNo())
                .reportNo(reportCategoryDTO.getReportNo())
                .build();
    }
}
