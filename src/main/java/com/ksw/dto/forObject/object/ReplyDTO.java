package com.ksw.dto.forObject.object;

import java.sql.Timestamp;

public class ReplyDTO {

    private Integer replyNo;
    private String replyContent;
    private Integer parentReply;
    private Boolean isActive;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // 기본 생성자
    public ReplyDTO() {}

    // Getters and Setters
    public Integer getReplyNo() {
        return replyNo;
    }

    public void setReplyNo(Integer replyNo) {
        this.replyNo = replyNo;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Integer getParentReply() {
        return parentReply;
    }

    public void setParentReply(Integer parentReply) {
        this.parentReply = parentReply;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
    // Builder 클래스
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

        public ReplyDTO build() {
            ReplyDTO replyDTO = new ReplyDTO();
            replyDTO.replyNo = this.replyNo;
            replyDTO.replyContent = this.replyContent;
            replyDTO.parentReply = this.parentReply;
            replyDTO.isActive = this.isActive;
            replyDTO.createdAt = this.createdAt;
            replyDTO.updatedAt = this.updatedAt;
            return replyDTO;
        }
    }
}
