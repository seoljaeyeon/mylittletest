package com.ksw.object.relation;

import java.io.Serializable;

import com.ksw.object.entity.Note;
import com.ksw.object.entity.User;

public class NoteUser implements Serializable{
	
	private static final long serialVersionUID = 1L;


    private User user;
    private Note note;

    // 기본 생성자
    public NoteUser() {}

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}
}
