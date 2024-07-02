package com.ksw.object.vo.relation;

import java.util.Objects;

public final class FileAnnouncementVO {
    private final Integer announcementNo;
    private final Integer fileNo;

    private FileAnnouncementVO(Builder builder) {
        this.announcementNo = builder.announcementNo;
        this.fileNo = builder.fileNo;
    }

    public Integer getAnnouncementNo() {
        return announcementNo;
    }

    public Integer getFileNo() {
        return fileNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileAnnouncementVO that = (FileAnnouncementVO) o;
        return Objects.equals(announcementNo, that.announcementNo) &&
                Objects.equals(fileNo, that.fileNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(announcementNo, fileNo);
    }

    @Override
    public String toString() {
        return "FileAnnouncementVO{" +
                "announcementNo=" + announcementNo +
                ", fileNo=" + fileNo +
                '}';
    }

    public static class Builder {
        private Integer announcementNo;
        private Integer fileNo;

        public Builder announcementNo(Integer announcementNo) {
            this.announcementNo = announcementNo;
            return this;
        }

        public Builder fileNo(Integer fileNo) {
            this.fileNo = fileNo;
            return this;
        }

        public FileAnnouncementVO build() {
            return new FileAnnouncementVO(this);
        }
    }
}
