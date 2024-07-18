package com.ksw.object.relation;

import java.io.Serializable;

import com.ksw.object.entity.User;

public class ReportManagement implements Serializable{
	private static final long serialVersionUID = 1L;

    private User user;
    private User manager;
    private User solver;

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public User getSolver() {
		return solver;
	}

	public void setSolver(User solver) {
		this.solver = solver;
	}

	// 기본 생성자
    public ReportManagement() {}

}
