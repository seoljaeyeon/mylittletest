package com.ksw.object.relation;

import java.io.Serializable;

import com.ksw.object.entity.Reply;
import com.ksw.object.entity.Report;
import com.ksw.object.entity.User;

public class ReportReply implements Serializable{
	private static final long serialVersionUID = 1L;

    private User user;
    private Reply reply;
    
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	private Report report;

    // 기본 생성자
    public ReportReply() {}
}
