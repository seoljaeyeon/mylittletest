package com.ksw.object.entity.mybatis;

public class ReportReply {

    private Integer userNo;
    private Integer replyNo;
    private Integer reportNo;

    // 기본 생성자
    public ReportReply() {}

    // Getter 및 Setter
    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public Integer getReplyNo() {
        return replyNo;
    }

    public void setReplyNo(Integer replyNo) {
        this.replyNo = replyNo;
    }

    public Integer getReportNo() {
        return reportNo;
    }

    public void setReportNo(Integer reportNo) {
        this.reportNo = reportNo;
    }
}
