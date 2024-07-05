package com.ksw.dto.forObject.entity;

import java.sql.Timestamp;

public class FavoriteDTO {

    private Integer favoriteNo;
    private Timestamp createdAt;

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
    // Builder 클래스
    public static class Builder {
        private Integer favoriteNo;
        private Timestamp createdAt;

        public Builder favoriteNo(Integer favoriteNo) {
            this.favoriteNo = favoriteNo;
            return this;
        }

        public Builder createdAt(Timestamp createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public FavoriteDTO build() {
            FavoriteDTO favoriteDTO = new FavoriteDTO();
            favoriteDTO.favoriteNo = this.favoriteNo;
            favoriteDTO.createdAt = this.createdAt;
            return favoriteDTO;
        }
    }
}
