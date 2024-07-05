package com.ksw.object.vo.relation;

import java.util.Objects;

public final class NoteUserVO {
    private final Integer userNo;
    private final Integer noteNo;

    private NoteUserVO(Builder builder) {
        this.userNo = builder.userNo;
        this.noteNo = builder.noteNo;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public Integer getNoteNo() {
        return noteNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteUserVO that = (NoteUserVO) o;
        return Objects.equals(userNo, that.userNo) &&
                Objects.equals(noteNo, that.noteNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userNo, noteNo);
    }

    @Override
    public String toString() {
        return "NoteUserVO{" +
                "userNo=" + userNo +
                ", noteNo=" + noteNo +
                '}';
    }

    public static class Builder {
        private Integer userNo;
        private Integer noteNo;

        public Builder userNo(Integer userNo) {
            this.userNo = userNo;
            return this;
        }

        public Builder noteNo(Integer noteNo) {
            this.noteNo = noteNo;
            return this;
        }

        public NoteUserVO build() {
            return new NoteUserVO(this);
        }
    }
}
