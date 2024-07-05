package com.ksw.object.vo.relation;

import java.util.Objects;

public final class FavoriteNoteVO {
    private final Integer userNo;
    private final Integer noteNo;
    private final Integer favoriteNo;

    private FavoriteNoteVO(Builder builder) {
        this.userNo = builder.userNo;
        this.noteNo = builder.noteNo;
        this.favoriteNo = builder.favoriteNo;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public Integer getNoteNo() {
        return noteNo;
    }

    public Integer getFavoriteNo() {
        return favoriteNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteNoteVO that = (FavoriteNoteVO) o;
        return Objects.equals(userNo, that.userNo) &&
                Objects.equals(noteNo, that.noteNo) &&
                Objects.equals(favoriteNo, that.favoriteNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userNo, noteNo, favoriteNo);
    }

    @Override
    public String toString() {
        return "FavoriteNoteVO{" +
                "userNo=" + userNo +
                ", noteNo=" + noteNo +
                ", favoriteNo=" + favoriteNo +
                '}';
    }

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

        public FavoriteNoteVO build() {
            return new FavoriteNoteVO(this);
        }
    }
}
