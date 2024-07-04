package com.ksw.vo.forObject.relation;

import java.util.Objects;

public final class FavoriteCategoryVO {
    private final Integer userNo;
    private final Integer categoryNo;
    private final Integer favoriteNo;

    private FavoriteCategoryVO(Builder builder) {
        this.userNo = builder.userNo;
        this.categoryNo = builder.categoryNo;
        this.favoriteNo = builder.favoriteNo;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public Integer getCategoryNo() {
        return categoryNo;
    }

    public Integer getFavoriteNo() {
        return favoriteNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteCategoryVO that = (FavoriteCategoryVO) o;
        return Objects.equals(userNo, that.userNo) &&
                Objects.equals(categoryNo, that.categoryNo) &&
                Objects.equals(favoriteNo, that.favoriteNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userNo, categoryNo, favoriteNo);
    }

    @Override
    public String toString() {
        return "FavoriteCategoryVO{" +
                "userNo=" + userNo +
                ", categoryNo=" + categoryNo +
                ", favoriteNo=" + favoriteNo +
                '}';
    }

    public static class Builder {
        private Integer userNo;
        private Integer categoryNo;
        private Integer favoriteNo;

        public Builder userNo(Integer userNo) {
            this.userNo = userNo;
            return this;
        }

        public Builder categoryNo(Integer categoryNo) {
            this.categoryNo = categoryNo;
            return this;
        }

        public Builder favoriteNo(Integer favoriteNo) {
            this.favoriteNo = favoriteNo;
            return this;
        }

        public FavoriteCategoryVO build() {
            return new FavoriteCategoryVO(this);
        }
    }
}
