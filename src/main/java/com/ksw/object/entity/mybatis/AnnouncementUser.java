package com.ksw.object.entity.mybatis;

public class AnnouncementUser {

    private Integer announcementNo;
    private Integer userNo;

    // 기본 생성자
    public AnnouncementUser() {}

    // Getter 및 Setter
    public Integer getAnnouncementNo() {
        return announcementNo;
    }

    public void setAnnouncementNo(Integer announcementNo) {
        this.announcementNo = announcementNo;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }
}
