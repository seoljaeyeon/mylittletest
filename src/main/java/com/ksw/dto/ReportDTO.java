package com.ksw.dto;

import java.sql.Timestamp;

public class ReportDTO {

    private Integer reportNo;
    private Integer reportType;
    private String reportNote;
    private Integer solvedType;
    private Timestamp createdAt;
    private Timestamp solvedAt;

    // 기본 생성자
    public ReportDTO() {}

    // Getters and Setters
    public Integer getReportNo() {
        return reportNo;
    }

    public void setReportNo(Integer reportNo) {
        this.reportNo = reportNo;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    public String getReportNote() {
        return reportNote;
    }

    public void setReportNote(String reportNote) {
        this.reportNote = reportNote;
    }

    public Integer getSolvedType() {
        return solvedType;
    }

    public void setSolvedType(Integer solvedType) {
        this.solvedType = solvedType;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getSolvedAt() {
        return solvedAt;
    }

    public void setSolvedAt(Timestamp solvedAt) {
        this.solvedAt = solvedAt;
    }
}
