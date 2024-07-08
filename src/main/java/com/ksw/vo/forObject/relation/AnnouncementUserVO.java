package com.ksw.vo.forObject.relation;

import java.util.Objects;

public final class AnnouncementUserVO {
    private final Integer announcementNo;
    private final Integer userNo;

    private AnnouncementUserVO(Builder builder) {
        this.announcementNo = builder.announcementNo;
        this.userNo = builder.userNo;
    }

    public Integer getAnnouncementNo() {
        return announcementNo;
    }

    public Integer getUserNo() {
        return userNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnnouncementUserVO that = (AnnouncementUserVO) o;
        return Objects.equals(announcementNo, that.announcementNo) &&
                Objects.equals(userNo, that.userNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(announcementNo, userNo);
    }

    @Override
    public String toString() {
        return "AnnouncementUserVO{" +
                "announcementNo=" + announcementNo +
                ", userNo=" + userNo +
                '}';
    }

    public static class Builder {
        private Integer announcementNo;
        private Integer userNo;

        public Builder announcementNo(Integer announcementNo) {
            this.announcementNo = announcementNo;
            return this;
        }

        public Builder userNo(Integer userNo) {
            this.userNo = userNo;
            return this;
        }

        public AnnouncementUserVO build() {
            return new AnnouncementUserVO(this);
        }
    }
}
