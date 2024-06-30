package com.ksw.dto;

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
}
