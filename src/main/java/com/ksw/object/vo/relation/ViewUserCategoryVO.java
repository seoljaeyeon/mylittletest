package com.ksw.object.vo.relation;

import java.util.Objects;

public final class ViewUserCategoryVO {
    private final Integer userNo;
    private final Integer categoryNo;
    private final Integer categoryViewNo;

    private ViewUserCategoryVO(Builder builder) {
        this.userNo = builder.userNo;
        this.categoryNo = builder.categoryNo;
        this.categoryViewNo = builder.categoryViewNo;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public Integer getCategoryNo() {
        return categoryNo;
    }

    public Integer getCategoryViewNo() {
        return categoryViewNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViewUserCategoryVO that = (ViewUserCategoryVO) o;
        return Objects.equals(userNo, that.userNo) &&
                Objects.equals(categoryNo, that.categoryNo) &&
                Objects.equals(categoryViewNo, that.categoryViewNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userNo, categoryNo, categoryViewNo);
    }

    @Override
    public String toString() {
        return "ViewUserCategoryVO{" +
                "userNo=" + userNo +
                ", categoryNo=" + categoryNo +
                ", categoryViewNo=" + categoryViewNo +
                '}';
    }

    public static class Builder {
        private Integer userNo;
        private Integer categoryNo;
        private Integer categoryViewNo;

        public Builder userNo(Integer userNo) {
            this.userNo = userNo;
            return this;
        }

        public Builder categoryNo(Integer categoryNo) {
            this.categoryNo = categoryNo;
            return this;
        }

        public Builder categoryViewNo(Integer categoryViewNo) {
            this.categoryViewNo = categoryViewNo;
            return this;
        }

        public ViewUserCategoryVO build() {
            return new ViewUserCategoryVO(this);
        }
    }
}
