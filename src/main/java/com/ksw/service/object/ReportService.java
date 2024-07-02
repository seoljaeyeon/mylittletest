package com.ksw.service.object;

import com.ksw.dto.forObject.ReportDTO;
import com.ksw.object.entity.jpa.Report;
import com.ksw.object.vo.ReportVO;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    // Entity -> DTO 변환 메소드
    public ReportDTO convertToDTO(Report reportEntity) {
        return new ReportDTO.Builder()
                .reportNo(reportEntity.getReportNo())
                .reportType(reportEntity.getReportType())
                .reportNote(reportEntity.getReportNote())
                .solvedType(reportEntity.getSolvedType())
                .createdAt(reportEntity.getCreatedAt())
                .solvedAt(reportEntity.getSolvedAt())
                .build();
    }

    // DTO -> VO 변환 메소드
    public ReportVO convertToVO(ReportDTO reportDTO) {
        return new ReportVO.Builder()
                .reportNo(reportDTO.getReportNo())
                .reportType(reportDTO.getReportType())
                .reportNote(reportDTO.getReportNote())
                .solvedType(reportDTO.getSolvedType())
                .createdAt(reportDTO.getCreatedAt())
                .solvedAt(reportDTO.getSolvedAt())
                .build();
    }
}
