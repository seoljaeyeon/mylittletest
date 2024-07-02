package com.ksw.object.vo;

import java.util.Objects;

public final class ReportCategoryVO {
    private final Integer userNo;
    private final Integer categoryNo;
    private final Integer reportNo;

    private ReportCategoryVO(Builder builder) {
        this.userNo = builder.userNo;
        this.categoryNo = builder.categoryNo;
        this.reportNo = builder.reportNo;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public Integer getCategoryNo() {
        return categoryNo;
    }

    public Integer getReportNo() {
        return reportNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportCategoryVO that = (ReportCategoryVO) o;
        return Objects.equals(userNo, that.userNo) &&
                Objects.equals(categoryNo, that.categoryNo) &&
                Objects.equals(reportNo, that.reportNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userNo, categoryNo, reportNo);
    }

    @Override
    public String toString() {
        return "ReportCategoryVO{" +
                "userNo=" + userNo +
                ", categoryNo=" + categoryNo +
                ", reportNo=" + reportNo +
                '}';
    }

    public static class Builder {
        private Integer userNo;
        private Integer categoryNo;
        private Integer reportNo;

        public Builder userNo(Integer userNo) {
            this.userNo = userNo;
            return this;
        }

        public Builder categoryNo(Integer categoryNo) {
            this.categoryNo = categoryNo;
            return this;
        }

        public Builder reportNo(Integer reportNo) {
            this.reportNo = reportNo;
            return this;
        }

        public ReportCategoryVO build() {
            return new ReportCategoryVO(this);
        }
    }
}
