package com.ksw.dto;

public class FavoriteCategoryDTO {

    private Integer userNo;
    private Integer categoryNo;
    private Integer favoriteNo;

    // 기본 생성자
    public FavoriteCategoryDTO() {}

    // Getter 및 Setter
    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public Integer getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(Integer categoryNo) {
        this.categoryNo = categoryNo;
    }

    public Integer getFavoriteNo() {
        return favoriteNo;
    }

    public void setFavoriteNo(Integer favoriteNo) {
        this.favoriteNo = favoriteNo;
    }

    // 빌더 패턴 구현
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

        public FavoriteCategoryDTO build() {
            FavoriteCategoryDTO favoriteCategoryDTO = new FavoriteCategoryDTO();
            favoriteCategoryDTO.userNo = this.userNo;
            favoriteCategoryDTO.categoryNo = this.categoryNo;
            favoriteCategoryDTO.favoriteNo = this.favoriteNo;
            return favoriteCategoryDTO;
        }
    }
}
