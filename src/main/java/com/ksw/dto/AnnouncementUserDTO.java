package com.ksw.dto;

public class AnnouncementUserDTO {

    private Integer announcementNo;
    private Integer userNo;

    // 기본 생성자
    public AnnouncementUserDTO() {}

    // Getter 및 Setter
    public Integer getAnnouncementNo() {
        return announcementNo;
    }

    public void setAnnouncementNo(Integer announcementNo) {
        this.announcementNo = announcementNo;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    // 빌더 패턴 구현
    public static class Builder {
        private Integer announcementNo;
        private Integer userNo;

        public Builder announcementNo(Integer announcementNo) {
            this.announcementNo = announcementNo;
            return this;
        }

        public Builder userNo(Integer userNo) {
            this.userNo = userNo;
            return this;
        }

        public AnnouncementUserDTO build() {
            AnnouncementUserDTO announcementUserDTO = new AnnouncementUserDTO();
            announcementUserDTO.announcementNo = this.announcementNo;
            announcementUserDTO.userNo = this.userNo;
            return announcementUserDTO;
        }
    }
}
