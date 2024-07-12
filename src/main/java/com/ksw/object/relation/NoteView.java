package com.ksw.object.relation;

import java.io.Serializable;

import com.ksw.object.entity.Note;
import com.ksw.object.entity.User;
import com.ksw.object.entity.View;

public class NoteView implements Serializable{
    
	
	private static final long serialVersionUID = 1L;

    private View view;
    private Note note;
    private User user;
    
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public View getView() {
		return view;
	}
	public void setView(View view) {
		this.view = view;
	}
	public Note getNote() {
		return note;
	}
	public void setNote(Note note) {
		this.note = note;
	}
}
