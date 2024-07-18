package com.ksw.service.forObject.entity;

import com.ksw.dto.forObject.entity.ReportDTO;
import com.ksw.object.entity.Report;
import com.ksw.vo.forObject.entity.ReportVO;

import org.springframework.stereotype.Service;

@Service
public class ReportService {

    // Entity -> DTO 변환 메소드
    public ReportDTO convertToDTO(Report reportEntity) {
    	ReportDTO dto = new ReportDTO();
    	if (reportEntity == null) {
    		System.out.println("Report to ReportDTO failed. Empty ReportDTO created. Report is null");
    		return dto;
    	}
    	dto.setCreatedAt(reportEntity.getCreatedAt());
    	dto.setReportNo(reportEntity.getReportNo());
    	dto.setReportNote(reportEntity.getReportNote());
    	dto.setReportType(reportEntity.getReportType());
    	dto.setSolvedAt(reportEntity.getSolvedAt());
    	dto.setSolvedType(reportEntity.getSolvedType());
        return dto;
    }

    // DTO -> VO 변환 메소드
    public ReportVO convertToVO(ReportDTO reportDTO) {
    	if (reportDTO == null) {
    		System.out.println("ReportDTO to ReportVO failed. Empty ReportVO created. ReportDTO is null");
    		return new ReportVO.Builder().build();
    	}
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
