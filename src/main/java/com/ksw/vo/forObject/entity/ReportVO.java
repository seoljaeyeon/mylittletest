package com.ksw.vo.forObject.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public final class ReportVO implements Serializable {

	private static final long serialVersionUID = 1L;
    private final Integer reportNo;
    private final Integer reportType;
    private final String reportNote;
    private final Integer solvedType;
    private final Timestamp createdAt;
    private final Timestamp solvedAt;

    private ReportVO(Builder builder) {
        this.reportNo = builder.reportNo;
        this.reportType = builder.reportType;
        this.reportNote = builder.reportNote;
        this.solvedType = builder.solvedType;
        this.createdAt = builder.createdAt;
        this.solvedAt = builder.solvedAt;
    }

    public Integer getReportNo() {
        return reportNo;
    }

    public Integer getReportType() {
        return reportType;
    }

    public String getReportNote() {
        return reportNote;
    }

    public Integer getSolvedType() {
        return solvedType;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getSolvedAt() {
        return solvedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportVO reportVO = (ReportVO) o;
        return Objects.equals(reportNo, reportVO.reportNo) &&
                Objects.equals(reportType, reportVO.reportType) &&
                Objects.equals(reportNote, reportVO.reportNote) &&
                Objects.equals(solvedType, reportVO.solvedType) &&
                Objects.equals(createdAt, reportVO.createdAt) &&
                Objects.equals(solvedAt, reportVO.solvedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reportNo, reportType, reportNote, solvedType, createdAt, solvedAt);
    }

    @Override
    public String toString() {
        return "ReportVO{" +
                "reportNo=" + reportNo +
                ", reportType=" + reportType +
                ", reportNote='" + reportNote + '\'' +
                ", solvedType=" + solvedType +
                ", createdAt=" + createdAt +
                ", solvedAt=" + solvedAt +
                '}';
    }

    public static class Builder {
        private Integer reportNo;
        private Integer reportType;
        private String reportNote;
        private Integer solvedType;
        private Timestamp createdAt;
        private Timestamp solvedAt;

        public Builder reportNo(Integer reportNo) {
            this.reportNo = reportNo;
            return this;
        }

        public Builder reportType(Integer reportType) {
            this.reportType = reportType;
            return this;
        }

        public Builder reportNote(String reportNote) {
            this.reportNote = reportNote;
            return this;
        }

        public Builder solvedType(Integer solvedType) {
            this.solvedType = solvedType;
            return this;
        }

        public Builder createdAt(Timestamp createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder solvedAt(Timestamp solvedAt) {
            this.solvedAt = solvedAt;
            return this;
        }

        public ReportVO build() {
            return new ReportVO(this);
        }
    }
}
