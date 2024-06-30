package com.ksw.dto;

import java.sql.Timestamp;

public class CategoryViewDTO {

    private Integer categoryViewNo;
    private Timestamp createdAt;

    // 기본 생성자
    public CategoryViewDTO() {}

    // Getters and Setters
    public Integer getCategoryViewNo() {
        return categoryViewNo;
    }

    public void setCategoryViewNo(Integer categoryViewNo) {
        this.categoryViewNo = categoryViewNo;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
