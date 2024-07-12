package com.ksw.object.relation;

import java.io.Serializable;

import com.ksw.object.entity.Category;
import com.ksw.object.entity.Report;
import com.ksw.object.entity.User;

public class ReportCategory implements Serializable{
	private static final long serialVersionUID = 1L;

    private User user;
    private Category category;
    private Report report;

    // 기본 생성자
    public ReportCategory() {}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

}
