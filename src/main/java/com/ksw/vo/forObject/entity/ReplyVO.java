package com.ksw.vo.forObject.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public final class ReplyVO implements Serializable {

	private static final long serialVersionUID = 1L;
    private final Integer replyNo;
    private final String replyContent;
    private final Integer parentReply;
    private final Boolean isActive;
    private final Timestamp createdAt;
    private final Timestamp updatedAt;

    private ReplyVO(Builder builder) {
        this.replyNo = builder.replyNo;
        this.replyContent = builder.replyContent;
        this.parentReply = builder.parentReply;
        this.isActive = builder.isActive;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public Integer getReplyNo() {
        return replyNo;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public Integer getParentReply() {
        return parentReply;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReplyVO replyVO = (ReplyVO) o;
        return Objects.equals(replyNo, replyVO.replyNo) &&
                Objects.equals(replyContent, replyVO.replyContent) &&
                Objects.equals(parentReply, replyVO.parentReply) &&
                Objects.equals(isActive, replyVO.isActive) &&
                Objects.equals(createdAt, replyVO.createdAt) &&
                Objects.equals(updatedAt, replyVO.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(replyNo, replyContent, parentReply, isActive, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "ReplyVO{" +
                "replyNo=" + replyNo +
                ", replyContent='" + replyContent + '\'' +
                ", parentReply=" + parentReply +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public static class Builder {
        private Integer replyNo;
        private String replyContent;
        private Integer parentReply;
        private Boolean isActive;
        private Timestamp createdAt;
        private Timestamp updatedAt;

        public Builder replyNo(Integer replyNo) {
            this.replyNo = replyNo;
            return this;
        }

        public Builder replyContent(String replyContent) {
            this.replyContent = replyContent;
            return this;
        }

        public Builder parentReply(Integer parentReply) {
            this.parentReply = parentReply;
            return this;
        }

        public Builder isActive(Boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public Builder createdAt(Timestamp createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(Timestamp updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public ReplyVO build() {
            return new ReplyVO(this);
        }
    }
}
