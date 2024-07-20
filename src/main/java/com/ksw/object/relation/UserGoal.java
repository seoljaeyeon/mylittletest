package com.ksw.object.relation;

import java.io.Serializable;
import java.sql.Timestamp;

import com.ksw.object.entity.Goal;
import com.ksw.object.entity.User;

public class UserGoal implements Serializable{
	
	private static final long	serialVersionUID = 1L;
	
	private User user;
	private Goal goal;
	private Timestamp createdAt;
	
	public UserGoal() {
		super();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Goal getGoal() {
		return goal;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	

}
