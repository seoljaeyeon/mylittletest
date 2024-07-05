package com.ksw.object.entity.mybatis;

public class FavoriteCategory {

    private Integer userNo;
    private Integer categoryNo;
    private Integer favoriteNo;

    // 기본 생성자
    public FavoriteCategory() {}

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
}
