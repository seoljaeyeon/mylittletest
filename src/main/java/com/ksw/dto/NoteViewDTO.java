package com.ksw.dto;

import java.sql.Timestamp;

public class NoteViewDTO {

    private Integer noteViewNo;
    private Timestamp createdAt;

    // 기본 생성자
    public NoteViewDTO() {}

    // Getters and Setters
    public Integer getNoteViewNo() {
        return noteViewNo;
    }

    public void setNoteViewNo(Integer noteViewNo) {
        this.noteViewNo = noteViewNo;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
