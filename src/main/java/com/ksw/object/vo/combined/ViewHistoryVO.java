package com.ksw.object.vo.combined;

import java.sql.Timestamp;
import java.util.Objects;

public final class ViewHistoryVO {
    private final Integer noteNo;
    private final Integer noteViewNo;
    private final Timestamp createAt;

    private ViewHistoryVO(Builder builder) {
        this.noteNo = builder.noteNo;
        this.noteViewNo = builder.noteViewNo;
        this.createAt = builder.createAt;
    }

    public Integer getNoteNo() {
        return noteNo;
    }

    public Integer getNoteViewNo() {
        return noteViewNo;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViewHistoryVO that = (ViewHistoryVO) o;
        return Objects.equals(noteNo, that.noteNo) &&
                Objects.equals(noteViewNo, that.noteViewNo) &&
                Objects.equals(createAt, that.createAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteNo, noteViewNo, createAt);
    }

    @Override
    public String toString() {
        return "ViewHistoryVO{" +
                "noteNo=" + noteNo +
                ", noteViewNo=" + noteViewNo +
                ", createAt=" + createAt +
                '}';
    }

    public static class Builder {
        private Integer noteNo;
        private Integer noteViewNo;
        private Timestamp createAt;

        public Builder noteNo(Integer noteNo) {
            this.noteNo = noteNo;
            return this;
        }

        public Builder noteViewNo(Integer noteViewNo) {
            this.noteViewNo = noteViewNo;
            return this;
        }

        public Builder createAt(Timestamp createAt) {
            this.createAt = createAt;
            return this;
        }

        public ViewHistoryVO build() {
            return new ViewHistoryVO(this);
        }
    }
}
