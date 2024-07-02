package com.ksw.object.vo;

import java.util.Objects;

public final class NoteReplyVO {
    private final Integer noteNo;
    private final Integer replyNo;

    private NoteReplyVO(Builder builder) {
        this.noteNo = builder.noteNo;
        this.replyNo = builder.replyNo;
    }

    public Integer getNoteNo() {
        return noteNo;
    }

    public Integer getReplyNo() {
        return replyNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteReplyVO that = (NoteReplyVO) o;
        return Objects.equals(noteNo, that.noteNo) &&
                Objects.equals(replyNo, that.replyNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteNo, replyNo);
    }

    @Override
    public String toString() {
        return "NoteReplyVO{" +
                "noteNo=" + noteNo +
                ", replyNo=" + replyNo +
                '}';
    }

    public static class Builder {
        private Integer noteNo;
        private Integer replyNo;

        public Builder noteNo(Integer noteNo) {
            this.noteNo = noteNo;
            return this;
        }

        public Builder replyNo(Integer replyNo) {
            this.replyNo = replyNo;
            return this;
        }

        public NoteReplyVO build() {
            return new NoteReplyVO(this);
        }
    }
}
