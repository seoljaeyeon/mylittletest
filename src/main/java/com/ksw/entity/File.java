package com.ksw.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "file")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fileNo;

    @Column(nullable = false, length = 255)
    private String savedName;

    @Column(nullable = false, length = 255)
    private String uploadName;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

	public Integer getFileNo() {
		return fileNo;
	}

	public String getSavedName() {
		return savedName;
	}

	public String getUploadName() {
		return uploadName;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setFileNo(Integer fileNo) {
		this.fileNo = fileNo;
	}

	public void setSavedName(String savedName) {
		this.savedName = savedName;
	}

	public void setUploadName(String uploadName) {
		this.uploadName = uploadName;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}
