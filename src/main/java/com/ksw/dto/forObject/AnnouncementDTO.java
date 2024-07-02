package com.ksw.dto.forObject;

import java.sql.Timestamp;

public class AnnouncementDTO {

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
    
    // Builder 패턴
    public static class Builder {
        private Integer announcementNo;
        private String announcementTitle;
        private String announcementContent;
        private Timestamp schedule;
        private Boolean isActive;
        private Timestamp createdAt;
        private Timestamp updatedAt;

        public Builder announcementNo(Integer announcementNo) {
            this.announcementNo = announcementNo;
            return this;
        }

        public Builder announcementTitle(String announcementTitle) {
            this.announcementTitle = announcementTitle;
            return this;
        }

        public Builder announcementContent(String announcementContent) {
            this.announcementContent = announcementContent;
            return this;
        }

        public Builder schedule(Timestamp schedule) {
            this.schedule = schedule;
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

        public AnnouncementDTO build() {
            AnnouncementDTO announcementDTO = new AnnouncementDTO();
            announcementDTO.announcementNo = this.announcementNo;
            announcementDTO.announcementTitle = this.announcementTitle;
            announcementDTO.announcementContent = this.announcementContent;
            announcementDTO.schedule = this.schedule;
            announcementDTO.isActive = this.isActive;
            announcementDTO.createdAt = this.createdAt;
            announcementDTO.updatedAt = this.updatedAt;
            return announcementDTO;
        }
    }
}
