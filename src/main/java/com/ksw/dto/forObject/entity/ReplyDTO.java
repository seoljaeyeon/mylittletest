package com.ksw.dto.forObject.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class ReplyDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	

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
}
