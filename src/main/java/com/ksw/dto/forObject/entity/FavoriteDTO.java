package com.ksw.dto.forObject.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class FavoriteDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private Integer favoriteNo;
    private Integer favoriteType;
    private Timestamp createdAt;
    
    public Integer getFavoriteType() {
		return favoriteType;
	}

	public void setFavoriteType(Integer favoriteType) {
		this.favoriteType = favoriteType;
	}

    // 기본 생성자
    public FavoriteDTO() {}

    // Getters and Setters
    public Integer getFavoriteNo() {
        return favoriteNo;
    }

    public void setFavoriteNo(Integer favoriteNo) {
        this.favoriteNo = favoriteNo;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
