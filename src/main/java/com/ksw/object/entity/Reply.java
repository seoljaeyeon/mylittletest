package com.ksw.object.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "reply")
public class Reply implements Serializable{

	private static final long serialVersionUID = 1L;
	
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

    
    // constructor
    public Reply() {
		super();
	}
	@PrePersist 
    protected void onCreate() { 
    Timestamp now = new Timestamp(System.currentTimeMillis()); 
    createdAt = now; updatedAt = now; 
    } 
    // 엔티티가 업데이트되기 전에 실행 
    @PreUpdate 
    protected void onUpdate() { 
    	updatedAt = Timestamp.valueOf(LocalDateTime.now());
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
