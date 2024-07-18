package com.ksw.vo.forObject.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public final class CategoryVO implements Serializable {

	private static final long serialVersionUID = 1L;
    private final Integer categoryNo;
    private final String categoryTitle;
    private final Boolean isActive;
    private final Timestamp createdAt;

    private CategoryVO(Builder builder) {
        this.categoryNo = builder.categoryNo;
        this.categoryTitle = builder.categoryTitle;
        this.isActive = builder.isActive;
        this.createdAt = builder.createdAt;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryVO that = (CategoryVO) o;
        return Objects.equals(categoryNo, that.categoryNo) &&
                Objects.equals(categoryTitle, that.categoryTitle) &&
                Objects.equals(isActive, that.isActive) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryNo, categoryTitle, isActive, createdAt);
    }

    @Override
    public String toString() {
        return "CategoryVO{" +
                "categoryNo=" + categoryNo +
                ", categoryTitle='" + categoryTitle + '\'' +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                '}';
    }

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

        public CategoryVO build() {
            return new CategoryVO(this);
        }
    }
}
