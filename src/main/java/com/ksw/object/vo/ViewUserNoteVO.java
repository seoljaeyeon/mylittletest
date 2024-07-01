package com.ksw.object.vo;

import java.util.Objects;

public final class ViewUserNoteVO {
    private final Integer userNo;
    private final Integer noteNo;
    private final Integer noteViewNo;

    private ViewUserNoteVO(Builder builder) {
        this.userNo = builder.userNo;
        this.noteNo = builder.noteNo;
        this.noteViewNo = builder.noteViewNo;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public Integer getNoteNo() {
        return noteNo;
    }

    public Integer getPostViewNo() {
        return noteViewNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViewUserNoteVO that = (ViewUserNoteVO) o;
        return Objects.equals(userNo, that.userNo) &&
                Objects.equals(noteNo, that.noteNo) &&
                Objects.equals(noteViewNo, that.noteViewNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userNo, noteNo, noteViewNo);
    }

    @Override
    public String toString() {
        return "ViewUserNoteVO{" +
                "userNo=" + userNo +
                ", noteNo=" + noteNo +
                ", noteViewNo=" + noteViewNo +
                '}';
    }

    public static class Builder {
        private Integer userNo;
        private Integer noteNo;
        private Integer noteViewNo;

        public Builder userNo(Integer userNo) {
            this.userNo = userNo;
            return this;
        }

        public Builder noteNo(Integer noteNo) {
            this.noteNo = noteNo;
            return this;
        }

        public Builder noteViewNo(Integer noteViewNo) {
            this.noteViewNo = noteViewNo;
            return this;
        }

        public ViewUserNoteVO build() {
            return new ViewUserNoteVO(this);
        }
    }
}
