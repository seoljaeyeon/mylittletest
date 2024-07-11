package com.ksw.object.relation;

import com.ksw.object.entity.Note;
import com.ksw.object.entity.User;

public class NoteUser {

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
