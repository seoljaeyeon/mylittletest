package com.ksw.dto.forObject.entity;

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
    
    // Builder 클래스
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

        public NoteViewDTO build() {
            NoteViewDTO noteViewDTO = new NoteViewDTO();
            noteViewDTO.noteViewNo = this.noteViewNo;
            noteViewDTO.createdAt = this.createdAt;
            return noteViewDTO;
        }
    }
}
