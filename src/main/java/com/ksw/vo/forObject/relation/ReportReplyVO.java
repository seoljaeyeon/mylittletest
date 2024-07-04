package com.ksw.vo.forObject.relation;

import java.util.Objects;

public final class ReportReplyVO {
    private final Integer userNo;
    private final Integer replyNo;
    private final Integer reportNo;

    private ReportReplyVO(Builder builder) {
        this.userNo = builder.userNo;
        this.replyNo = builder.replyNo;
        this.reportNo = builder.reportNo;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public Integer getReplyNo() {
        return replyNo;
    }

    public Integer getReportNo() {
        return reportNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportReplyVO that = (ReportReplyVO) o;
        return Objects.equals(userNo, that.userNo) &&
                Objects.equals(replyNo, that.replyNo) &&
                Objects.equals(reportNo, that.reportNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userNo, replyNo, reportNo);
    }

    @Override
    public String toString() {
        return "ReportReplyVO{" +
                "userNo=" + userNo +
                ", replyNo=" + replyNo +
                ", reportNo=" + reportNo +
                '}';
    }

    public static class Builder {
        private Integer userNo;
        private Integer replyNo;
        private Integer reportNo;

        public Builder userNo(Integer userNo) {
            this.userNo = userNo;
            return this;
        }

        public Builder replyNo(Integer replyNo) {
            this.replyNo = replyNo;
            return this;
        }

        public Builder reportNo(Integer reportNo) {
            this.reportNo = reportNo;
            return this;
        }

        public ReportReplyVO build() {
            return new ReportReplyVO(this);
        }
    }
}
