package com.ksw.object.vo.relation;

import java.util.Objects;

public final class ReportNoteVO {
    private final Integer userNo;
    private final Integer noteNo;
    private final Integer reportNo;

    private ReportNoteVO(Builder builder) {
        this.userNo = builder.userNo;
        this.noteNo = builder.noteNo;
        this.reportNo = builder.reportNo;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public Integer getNoteNo() {
        return noteNo;
    }

    public Integer getReportNo() {
        return reportNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportNoteVO that = (ReportNoteVO) o;
        return Objects.equals(userNo, that.userNo) &&
                Objects.equals(noteNo, that.noteNo) &&
                Objects.equals(reportNo, that.reportNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userNo, noteNo, reportNo);
    }

    @Override
    public String toString() {
        return "ReportNoteVO{" +
                "userNo=" + userNo +
                ", noteNo=" + noteNo +
                ", reportNo=" + reportNo +
                '}';
    }

    public static class Builder {
        private Integer userNo;
        private Integer noteNo;
        private Integer reportNo;

        public Builder userNo(Integer userNo) {
            this.userNo = userNo;
            return this;
        }

        public Builder noteNo(Integer noteNo) {
            this.noteNo = noteNo;
            return this;
        }

        public Builder reportNo(Integer reportNo) {
            this.reportNo = reportNo;
            return this;
        }

        public ReportNoteVO build() {
            return new ReportNoteVO(this);
        }
    }
}
