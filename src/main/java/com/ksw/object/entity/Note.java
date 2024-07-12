package com.ksw.object.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "note")
public class Note implements Serializable{

	private static final long serialVersionUID = 1L;

	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noteNo;

    @Column(nullable = false, length = 255)
    private String noteTitle;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String noteContent;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String noteCommentary;

    @Column(nullable = false, length = 255)
    private String noteHint;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String noteAnswer;

    @Column(columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean isActive = true;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    // constructor   
    public Note() {
		super();
	}

	@Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt;
    
    // 엔티티가 처음 저장되기 전에 실행 
    @PrePersist 
    protected void onCreate() { 
    	Timestamp now = new Timestamp(System.currentTimeMillis()); 
    	createdAt = now; updatedAt = now; 
    } 
    // 엔티티가 업데이트되기 전에 실행 
    @PreUpdate 
    protected void onUpdate() { 
    	updatedAt = Timestamp.valueOf(LocalDateTime.now());
    }
    
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
	
	public String getNoteCommentary() {
		return noteCommentary;
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

	public void setNoteCommentary(String noteCommentary) {
		this.noteCommentary = noteCommentary;
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
