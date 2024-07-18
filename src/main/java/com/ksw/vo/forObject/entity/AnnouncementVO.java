package com.ksw.vo.forObject.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public final class AnnouncementVO implements Serializable {

	private static final long serialVersionUID = 1L;
    private final Integer announcementNo;
    private final String announcementTitle;
    private final String announcementContent;
    private final Timestamp schedule;
    private final Boolean isActive;
    private final Timestamp createdAt;
    private final Timestamp updatedAt;

    private AnnouncementVO(Builder builder) {
        this.announcementNo = builder.announcementNo;
        this.announcementTitle = builder.announcementTitle;
        this.announcementContent = builder.announcementContent;
        this.schedule = builder.schedule;
        this.isActive = builder.isActive;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnnouncementVO that = (AnnouncementVO) o;
        return Objects.equals(announcementNo, that.announcementNo) &&
                Objects.equals(announcementTitle, that.announcementTitle) &&
                Objects.equals(announcementContent, that.announcementContent) &&
                Objects.equals(schedule, that.schedule) &&
                Objects.equals(isActive, that.isActive) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(announcementNo, announcementTitle, announcementContent, schedule, isActive, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "AnnouncementVO{" +
                "announcementNo=" + announcementNo +
                ", announcementTitle='" + announcementTitle + '\'' +
                ", announcementContent='" + announcementContent + '\'' +
                ", schedule='" + schedule + '\'' +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

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

        public AnnouncementVO build() {
            return new AnnouncementVO(this);
        }
    }
}
