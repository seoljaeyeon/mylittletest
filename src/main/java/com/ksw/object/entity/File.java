package com.ksw.object.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "file")
public class File implements Serializable{

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fileNo;

    @Column(nullable = false, length = 255)
    private String savedName;

    @Column(nullable = false, length = 255)
    private String uploadName;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    
    // constructor
	public File() {
		super();
	}
	
	// 엔티티가 처음 저장되기 전에 실행
    @PrePersist
    protected void onCreate() {
    	createdAt = Timestamp.valueOf(LocalDateTime.now());
    }

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
