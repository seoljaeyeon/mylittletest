package com.ksw.dto.forObject.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class NoteDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	

    private Integer noteNo;
    private String noteTitle;
    private String noteContent;
    private String noteCommentary;

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
    public String getNoteCommentary() {
		return noteCommentary;
	}

	public void setNoteCommentary(String noteCommentary) {
		this.noteCommentary = noteCommentary;
	}

}
