package com.ksw.object.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "goal")
public class Goal implements Serializable{

	private static final long serialversionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer goalNo;
	
	@Column(nullable = false)
	private Integer goalCount;
	
	public Goal() {
		super();
	}

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
