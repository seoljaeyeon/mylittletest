package com.ksw.vo;

public class ReportNoteVO {

    private Integer userNo;
    private Integer noteNo;
    private Integer reportNo;

    // 기본 생성자
    public ReportNoteVO() {}

    // Getter 및 Setter
    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public Integer getNoteNo() {
        return noteNo;
    }

    public void setNoteNo(Integer noteNo) {
        this.noteNo = noteNo;
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
            ReportNoteVO reportNoteVO = new ReportNoteVO();
            reportNoteVO.userNo = this.userNo;
            reportNoteVO.noteNo = this.noteNo;
            reportNoteVO.reportNo = this.reportNo;
            return reportNoteVO;
        }
    }
}
