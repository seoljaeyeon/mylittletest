package com.ksw.object.relation;

import com.ksw.object.entity.Note;
import com.ksw.object.entity.Report;
import com.ksw.object.entity.User;

public class ReportNote {

    private User user;
    private Note note;
    private Report report;

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

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	// 기본 생성자
    public ReportNote() {}
}
