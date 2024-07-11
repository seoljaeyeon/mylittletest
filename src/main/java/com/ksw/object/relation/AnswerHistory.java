package com.ksw.object.relation;

import java.sql.Timestamp;

import com.ksw.object.entity.Answer;
import com.ksw.object.entity.Note;
import com.ksw.object.entity.User;


public class AnswerHistory {
    private Note note;
    private Answer answer;
    private User user;
	public Note getNote() {
		return note;
	}
	public void setNote(Note note) {
		this.note = note;
	}
	public Answer getAnswer() {
		return answer;
	}
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}