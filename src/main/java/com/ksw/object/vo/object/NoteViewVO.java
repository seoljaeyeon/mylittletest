package com.ksw.object.vo.object;

import java.sql.Timestamp;
import java.util.Objects;

public final class NoteViewVO {
    private final Integer noteViewNo;
    private final Timestamp createdAt;

    private NoteViewVO(Builder builder) {
        this.noteViewNo = builder.noteViewNo;
        this.createdAt = builder.createdAt;
    }

    public Integer getNoteViewNo() {
        return noteViewNo;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteViewVO that = (NoteViewVO) o;
        return Objects.equals(noteViewNo, that.noteViewNo) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteViewNo, createdAt);
    }

    @Override
    public String toString() {
        return "NoteViewVO{" +
                "noteViewNo=" + noteViewNo +
                ", createdAt=" + createdAt +
                '}';
    }

    public static class Builder {
        private Integer noteViewNo;
        private Timestamp createdAt;

        public Builder noteViewNo(Integer noteViewNo) {
            this.noteViewNo = noteViewNo;
            return this;
        }

        public Builder createdAt(Timestamp createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public NoteViewVO build() {
            return new NoteViewVO(this);
        }
    }
}
