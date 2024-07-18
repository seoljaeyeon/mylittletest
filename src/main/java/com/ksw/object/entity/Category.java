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
@Table(name = "category")
public class Category implements Serializable{

	private static final long serialVersionUID = 1L;

	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryNo;

    @Column(nullable = false, length = 255)
    private String categoryTitle;

    @Column(columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean isActive = true;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    // 기본 생성자
    public Category() {
    	
    }
    
    @PrePersist
    protected void onCreate() {
    	createdAt = Timestamp.valueOf(LocalDateTime.now());
    }

    
	public Integer getCategoryNo() {
		return categoryNo;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCategoryNo(Integer categoryNo) {
		this.categoryNo = categoryNo;
	}

    
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}
