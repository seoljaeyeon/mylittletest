package com.ksw.dto.forObject.object;

import java.sql.Timestamp;

public class CategoryDTO {

    private Integer categoryNo;
    private String categoryTitle;
    private Boolean isActive;
    private Timestamp createdAt;

    // 기본 생성자
    public CategoryDTO() {}

    // Getters and Setters
    public Integer getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(Integer categoryNo) {
        this.categoryNo = categoryNo;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    // Builder 클래스
    public static class Builder {
        private Integer categoryNo;
        private String categoryTitle;
        private Boolean isActive;
        private Timestamp createdAt;

        public Builder categoryNo(Integer categoryNo) {
            this.categoryNo = categoryNo;
            return this;
        }

        public Builder categoryTitle(String categoryTitle) {
            this.categoryTitle = categoryTitle;
            return this;
        }

        public Builder isActive(Boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public Builder createdAt(Timestamp createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public CategoryDTO build() {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.categoryNo = this.categoryNo;
            categoryDTO.categoryTitle = this.categoryTitle;
            categoryDTO.isActive = this.isActive;
            categoryDTO.createdAt = this.createdAt;
            return categoryDTO;
        }
    }
}
