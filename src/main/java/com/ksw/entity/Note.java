package com.ksw.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noteNo;

    @Column(nullable = false, length = 255)
    private String noteTitle;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String noteContent;

    @Column(nullable = false, length = 255)
    private String noteHint;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String noteAnswer;

    @Column(columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean isActive = true;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

	public Integer getNoteNo() {
		return noteNo;
	}

	public String getNoteTitle() {
		return noteTitle;
	}

	public String getNoteContent() {
		return noteContent;
	}

	public String getNoteHint() {
		return noteHint;
	}

	public String getNoteAnswer() {
		return noteAnswer;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setNoteNo(Integer noteNo) {
		this.noteNo = noteNo;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public void setNoteHint(String noteHint) {
		this.noteHint = noteHint;
	}

	public void setNoteAnswer(String noteAnswer) {
		this.noteAnswer = noteAnswer;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
}
