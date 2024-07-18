package com.ksw.dto.forObject.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class AnnouncementDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private Integer announcementNo;
    private String announcementTitle;
    private String announcementContent;
    private Timestamp schedule;
    private Boolean isActive;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // 기본 생성자
    public AnnouncementDTO() {}

    // Getters and Setters
    public Integer getAnnouncementNo() {
        return announcementNo;
    }

    public void setAnnouncementNo(Integer announcementNo) {
        this.announcementNo = announcementNo;
    }

    public String getAnnouncementTitle() {
        return announcementTitle;
    }

    public void setAnnouncementTitle(String announcementTitle) {
        this.announcementTitle = announcementTitle;
    }

    public String getAnnouncementContent() {
        return announcementContent;
    }

    public void setAnnouncementContent(String announcementContent) {
        this.announcementContent = announcementContent;
    }

    public Timestamp getSchedule() {
        return schedule;
    }

    public void setSchedule(Timestamp schedule) {
        this.schedule = schedule;
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
