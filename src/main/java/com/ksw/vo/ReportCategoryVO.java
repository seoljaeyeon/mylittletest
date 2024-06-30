package com.ksw.vo;

public class ReportCategoryVO {

    private Integer userNo;
    private Integer categoryNo;
    private Integer reportNo;

    // 기본 생성자
    public ReportCategoryVO() {}

    // Getter 및 Setter
    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public Integer getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(Integer categoryNo) {
        this.categoryNo = categoryNo;
    }

    public Integer getReportNo() {
        return reportNo;
    }

    public void setReportNo(Integer reportNo) {
        this.reportNo = reportNo;
    }

    // 빌더 패턴 구현
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
            ReportCategoryVO reportCategoryVO = new ReportCategoryVO();
            reportCategoryVO.userNo = this.userNo;
            reportCategoryVO.categoryNo = this.categoryNo;
            reportCategoryVO.reportNo = this.reportNo;
            return reportCategoryVO;
        }
    }
}
