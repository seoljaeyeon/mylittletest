package com.ksw.object.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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

    @PrePersist
    protected void onCreate() {
        createdAt = Timestamp.valueOf(LocalDateTime.now());
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
