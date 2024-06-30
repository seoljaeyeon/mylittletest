package com.ksw.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "reply")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer replyNo;

    @Column(nullable = false, length = 255)
    private String replyContent;

    @Column
    private Integer parentReply;

    @Column(columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean isActive = true;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

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

	public void setReplyNo(Integer replyNo) {
		this.replyNo = replyNo;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public void setParentReply(Integer parentReply) {
		this.parentReply = parentReply;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
}
