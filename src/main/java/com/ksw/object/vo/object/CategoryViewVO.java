package com.ksw.object.vo.object;

import java.sql.Timestamp;
import java.util.Objects;

public final class CategoryViewVO {
    private final Integer categoryViewNo;
    private final Timestamp createdAt;

    private CategoryViewVO(Builder builder) {
        this.categoryViewNo = builder.categoryViewNo;
        this.createdAt = builder.createdAt;
    }

    public Integer getCategoryViewNo() {
        return categoryViewNo;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryViewVO that = (CategoryViewVO) o;
        return Objects.equals(categoryViewNo, that.categoryViewNo) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryViewNo, createdAt);
    }

    @Override
    public String toString() {
        return "CategoryViewVO{" +
                "categoryViewNo=" + categoryViewNo +
                ", createdAt=" + createdAt +
                '}';
    }

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

        public CategoryViewVO build() {
            return new CategoryViewVO(this);
        }
    }
}
