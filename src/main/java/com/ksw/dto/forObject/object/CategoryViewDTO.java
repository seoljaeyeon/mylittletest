package com.ksw.dto.forObject.object;

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
    // Builder 클래스
    public static class Builder {
        private Integer categoryViewNo;
        private Timestamp createdAt;

        public Builder categoryViewNo(Integer categoryViewNo) {
            this.categoryViewNo = categoryViewNo;
            return this;
        }

        public Builder createdAt(Timestamp createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public CategoryViewDTO build() {
            CategoryViewDTO categoryViewDTO = new CategoryViewDTO();
            categoryViewDTO.categoryViewNo = this.categoryViewNo;
            categoryViewDTO.createdAt = this.createdAt;
            return categoryViewDTO;
        }
    }
}
