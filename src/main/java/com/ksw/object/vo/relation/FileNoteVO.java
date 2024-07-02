package com.ksw.object.vo.relation;

import java.util.Objects;

public final class FileNoteVO {
    private final Integer noteNo;
    private final Integer fileNo;

    private FileNoteVO(Builder builder) {
        this.noteNo = builder.noteNo;
        this.fileNo = builder.fileNo;
    }

    public Integer getNoteNo() {
        return noteNo;
    }

    public Integer getFileNo() {
        return fileNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileNoteVO that = (FileNoteVO) o;
        return Objects.equals(noteNo, that.noteNo) &&
                Objects.equals(fileNo, that.fileNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteNo, fileNo);
    }

    @Override
    public String toString() {
        return "FileNoteVO{" +
                "noteNo=" + noteNo +
                ", fileNo=" + fileNo +
                '}';
    }

    public static class Builder {
        private Integer noteNo;
        private Integer fileNo;

        public Builder noteNo(Integer noteNo) {
            this.noteNo = noteNo;
            return this;
        }

        public Builder fileNo(Integer fileNo) {
            this.fileNo = fileNo;
            return this;
        }

        public FileNoteVO build() {
            return new FileNoteVO(this);
        }
    }
}
