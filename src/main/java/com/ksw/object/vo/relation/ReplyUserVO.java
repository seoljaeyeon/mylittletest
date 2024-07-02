package com.ksw.object.vo.relation;

import java.util.Objects;

public final class ReplyUserVO {
    private final Integer userNo;
    private final Integer replyNo;

    private ReplyUserVO(Builder builder) {
        this.userNo = builder.userNo;
        this.replyNo = builder.replyNo;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public Integer getReplyNo() {
        return replyNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReplyUserVO that = (ReplyUserVO) o;
        return Objects.equals(userNo, that.userNo) &&
                Objects.equals(replyNo, that.replyNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userNo, replyNo);
    }

    @Override
    public String toString() {
        return "ReplyUserVO{" +
                "userNo=" + userNo +
                ", replyNo=" + replyNo +
                '}';
    }

    public static class Builder {
        private Integer userNo;
        private Integer replyNo;

        public Builder userNo(Integer userNo) {
            this.userNo = userNo;
            return this;
        }

        public Builder replyNo(Integer replyNo) {
            this.replyNo = replyNo;
            return this;
        }

        public ReplyUserVO build() {
            return new ReplyUserVO(this);
        }
    }
}
