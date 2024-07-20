package com.ksw.dto.forObject.entity;

import java.io.Serializable;

public class GoalDTO implements Serializable{

	private static final long serialversionUID = 1L;
	
	private Integer goalNo;
	private Integer goalCount;
	public Integer getGoalNo() {
		return goalNo;
	}
	public void setGoalNo(Integer goalNo) {
		this.goalNo = goalNo;
	}
	public Integer getGoalCount() {
		return goalCount;
	}
	public void setGoalCount(Integer goalCount) {
		this.goalCount = goalCount;
	}
	
}
