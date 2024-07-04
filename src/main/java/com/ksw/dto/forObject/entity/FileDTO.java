package com.ksw.dto.forObject.entity;

import java.sql.Timestamp;

public class FileDTO {

    private Integer fileNo;
    private String savedName;
    private String uploadName;
    private Timestamp createdAt;

    // 기본 생성자
    public FileDTO() {}

    // Getters and Setters
    public Integer getFileNo() {
        return fileNo;
    }

    public void setFileNo(Integer fileNo) {
        this.fileNo = fileNo;
    }

    public String getSavedName() {
        return savedName;
    }

    public void setSavedName(String savedName) {
        this.savedName = savedName;
    }

    public String getUploadName() {
        return uploadName;
    }

    public void setUploadName(String uploadName) {
        this.uploadName = uploadName;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    // Builder 클래스
    public static class Builder {
        private Integer fileNo;
        private String savedName;
        private String uploadName;
        private Timestamp createdAt;

        public Builder fileNo(Integer fileNo) {
            this.fileNo = fileNo;
            return this;
        }

        public Builder savedName(String savedName) {
            this.savedName = savedName;
            return this;
        }

        public Builder uploadName(String uploadName) {
            this.uploadName = uploadName;
            return this;
        }

        public Builder createdAt(Timestamp createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public FileDTO build() {
            FileDTO fileDTO = new FileDTO();
            fileDTO.fileNo = this.fileNo;
            fileDTO.savedName = this.savedName;
            fileDTO.uploadName = this.uploadName;
            fileDTO.createdAt = this.createdAt;
            return fileDTO;
        }
    }
}
