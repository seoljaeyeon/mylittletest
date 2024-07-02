package com.ksw.object.entity.mybatis;

public class NoteUser {

    private Integer userNo;
    private Integer noteNo;

    // 기본 생성자
    public NoteUser() {}

    // Getter 및 Setter
    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public Integer getNoteNo() {
        return noteNo;
    }

    public void setNoteNo(Integer noteNo) {
        this.noteNo = noteNo;
    }
}
