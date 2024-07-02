package com.ksw.object.entity.mybatis;

public class ViewUserCategory {

    private Integer userNo;
    private Integer categoryNo;
    private Integer categoryViewNo;

    // 기본 생성자
    public ViewUserCategory() {}

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

    public Integer getCategoryViewNo() {
        return categoryViewNo;
    }

    public void setCategoryViewNo(Integer categoryViewNo) {
        this.categoryViewNo = categoryViewNo;
    }
}
