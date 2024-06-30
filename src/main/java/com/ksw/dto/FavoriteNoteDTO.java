package com.ksw.dto;

public class FavoriteNoteDTO {

    private Integer userNo;
    private Integer noteNo;
    private Integer favoriteNo;

    // 기본 생성자
    public FavoriteNoteDTO() {}

    // Getter 및 Setter
    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public Integer getNoteNo() {
        return noteNo;
    }

    public void setNoteNo(Integer noteNo) {
        this.noteNo = noteNo;
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
        private Integer noteNo;
        private Integer favoriteNo;

        public Builder userNo(Integer userNo) {
            this.userNo = userNo;
            return this;
        }

        public Builder noteNo(Integer noteNo) {
            this.noteNo = noteNo;
            return this;
        }

        public Builder favoriteNo(Integer favoriteNo) {
            this.favoriteNo = favoriteNo;
            return this;
        }

        public FavoriteNoteDTO build() {
            FavoriteNoteDTO favoriteNoteDTO = new FavoriteNoteDTO();
            favoriteNoteDTO.userNo = this.userNo;
            favoriteNoteDTO.noteNo = this.noteNo;
            favoriteNoteDTO.favoriteNo = this.favoriteNo;
            return favoriteNoteDTO;
        }
    }
}
