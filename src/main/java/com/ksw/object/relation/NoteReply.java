package com.ksw.object.relation;

import java.io.Serializable;

import com.ksw.object.entity.Note;
import com.ksw.object.entity.Reply;

public class NoteReply implements Serializable{

	private static final long serialVersionUID = 1L;

	
    private Note note;
    private Reply reply;

    // 기본 생성자
    public NoteReply() {}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}

}
