package com.ksw.object.vo.relation;

import java.util.Objects;

public final class NoteCategoryVO {
    private final Integer categoryNo;
    private final Integer noteNo;

    private NoteCategoryVO(Builder builder) {
        this.categoryNo = builder.categoryNo;
        this.noteNo = builder.noteNo;
    }

    public Integer getCategoryNo() {
        return categoryNo;
    }

    public Integer getNoteNo() {
        return noteNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteCategoryVO that = (NoteCategoryVO) o;
        return Objects.equals(categoryNo, that.categoryNo) &&
                Objects.equals(noteNo, that.noteNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryNo, noteNo);
    }

    @Override
    public String toString() {
        return "NoteCategoryVO{" +
                "categoryNo=" + categoryNo +
                ", noteNo=" + noteNo +
                '}';
    }

    public static class Builder {
        private Integer categoryNo;
        private Integer noteNo;

        public Builder categoryNo(Integer categoryNo) {
            this.categoryNo = categoryNo;
            return this;
        }

        public Builder noteNo(Integer noteNo) {
            this.noteNo = noteNo;
            return this;
        }

        public NoteCategoryVO build() {
            return new NoteCategoryVO(this);
        }
    }
}
