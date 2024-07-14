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
@Table(name = "announcement")
public class Announcement implements Serializable{

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer announcementNo;

    @Column(nullable = false, length = 255)
    private String announcementTitle;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String announcementContent;

    @Column(nullable = false, length = 50)
    private Timestamp schedule;

    @Column(columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean isActive = true;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt ;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt ;
    
    // 기본 생성자
    public Announcement() {
    }
    
    // 엔티티가 처음 저장되기 전에 실행 
    @PrePersist 
    protected void onCreate() { 
    	Timestamp now = Timestamp.valueOf(LocalDateTime.now()); 
    	createdAt = now; updatedAt = now; 
    } 
    // 엔티티가 업데이트되기 전에 실행 
    @PreUpdate 
    protected void onUpdate() { 
    	updatedAt = Timestamp.valueOf(LocalDateTime.now());
    }

	public Integer getAnnouncementNo() {
		return announcementNo;
	}

	public String getAnnouncementTitle() {
		return announcementTitle;
	}

	public String getAnnouncementContent() {
		return announcementContent;
	}

	public Timestamp getSchedule() {
		return schedule;
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

	public void setAnnouncementNo(Integer announcementNo) {
		this.announcementNo = announcementNo;
	}

	public void setAnnouncementTitle(String announcementTitle) {
		this.announcementTitle = announcementTitle;
	}

	public void setAnnouncementContent(String announcementContent) {
		this.announcementContent = announcementContent;
	}

	public void setSchedule(Timestamp schedule) {
		this.schedule = schedule;
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
