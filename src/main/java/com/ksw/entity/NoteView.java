package com.ksw.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "note_view")
public class NoteView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noteViewNo;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

	public Integer getNoteViewNo() {
		return noteViewNo;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setNoteViewNo(Integer noteViewNo) {
		this.noteViewNo = noteViewNo;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}
