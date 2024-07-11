package com.ksw.dto.forObject.entity;

import java.sql.Timestamp;

public class ViewDTO {

    private Integer viewNo;
    private Timestamp createdAt;

    // 기본 생성자
    public ViewDTO() {}

    // Getter 및 Setter
    public Integer getViewNo() {
        return viewNo;
    }

    public void setViewNo(Integer viewNo) {
        this.viewNo = viewNo;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
