package com.ksw.dto.forObject.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class FileDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
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
