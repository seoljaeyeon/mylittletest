package com.ksw.dto;

public class FileAnnouncementDTO {

    private Integer announcementNo;
    private Integer fileNo;

    // 기본 생성자
    public FileAnnouncementDTO() {}

    // Getter 및 Setter
    public Integer getAnnouncementNo() {
        return announcementNo;
    }

    public void setAnnouncementNo(Integer announcementNo) {
        this.announcementNo = announcementNo;
    }

    public Integer getFileNo() {
        return fileNo;
    }

    public void setFileNo(Integer fileNo) {
        this.fileNo = fileNo;
    }

    // 빌더 패턴 구현
    public static class Builder {
        private Integer announcementNo;
        private Integer fileNo;

        public Builder announcementNo(Integer announcementNo) {
            this.announcementNo = announcementNo;
            return this;
        }

        public Builder fileNo(Integer fileNo) {
            this.fileNo = fileNo;
            return this;
        }

        public FileAnnouncementDTO build() {
            FileAnnouncementDTO fileAnnouncementDTO = new FileAnnouncementDTO();
            fileAnnouncementDTO.announcementNo = this.announcementNo;
            fileAnnouncementDTO.fileNo = this.fileNo;
            return fileAnnouncementDTO;
        }
    }
}
