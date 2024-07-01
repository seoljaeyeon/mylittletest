package com.ksw.dto.forObject;

import java.sql.Timestamp;

public class NoteDTO {

    private Integer noteNo;
    private String noteTitle;
    private String noteContent;
    private String noteHint;
    private String noteAnswer;
    private Boolean isActive;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // 기본 생성자
    public NoteDTO() {}

    // Getters and Setters
    public Integer getNoteNo() {
        return noteNo;
    }

    public void setNoteNo(Integer noteNo) {
        this.noteNo = noteNo;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public String getNoteHint() {
        return noteHint;
    }

    public void setNoteHint(String noteHint) {
        this.noteHint = noteHint;
    }

    public String getNoteAnswer() {
        return noteAnswer;
    }

    public void setNoteAnswer(String noteAnswer) {
        this.noteAnswer = noteAnswer;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
    public static class Builder {
        private Integer noteNo;
        private String noteTitle;
        private String noteContent;
        private String noteHint;
        private String noteAnswer;
        private Boolean isActive;
        private Timestamp createdAt;
        private Timestamp updatedAt;

        public Builder noteNo(Integer noteNo) {
            this.noteNo = noteNo;
            return this;
        }

        public Builder noteTitle(String noteTitle) {
            this.noteTitle = noteTitle;
            return this;
        }

        public Builder noteContent(String noteContent) {
            this.noteContent = noteContent;
            return this;
        }

        public Builder noteHint(String noteHint) {
            this.noteHint = noteHint;
            return this;
        }

        public Builder noteAnswer(String noteAnswer) {
            this.noteAnswer = noteAnswer;
            return this;
        }

        public Builder isActive(Boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public Builder createdAt(Timestamp createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(Timestamp updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public NoteDTO build() {
            NoteDTO noteDTO = new NoteDTO();
            noteDTO.noteNo = this.noteNo;
            noteDTO.noteTitle = this.noteTitle;
            noteDTO.noteContent = this.noteContent;
            noteDTO.noteHint = this.noteHint;
            noteDTO.noteAnswer = this.noteAnswer;
            noteDTO.isActive = this.isActive;
            noteDTO.createdAt = this.createdAt;
            noteDTO.updatedAt = this.updatedAt;
            return noteDTO;
        }
    }
}
