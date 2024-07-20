package com.ksw.vo.forObject.entity;

import java.io.Serializable;
import java.util.Objects;

public class GoalVO implements Serializable{

	private static final long serialversionUID = 1L;
	
	private Integer goalNo;
	private Integer goalCount;
	
	public Integer getGoalNo() {
		return goalNo;
	}
	
	public Integer getGoualCount() {
		return goalCount;
	}

	private GoalVO(Builder builder) {
		this.goalNo = builder.goalNo;
		this.goalCount = builder.goalCount;
	}
	
	public static class Builder {
		private Integer goalNo;
		private Integer goalCount;
		
		public Builder goalNo(Integer goalNo) {
			this.goalNo = goalNo;
			return this;
		}
		
		public Builder goalCount(Integer goalCount) {
			this.goalCount = goalCount;
			return this;
		}
		
		public GoalVO build() {
			return new GoalVO(this);
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(goalCount, goalNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GoalVO other = (GoalVO) obj;
		return Objects.equals(goalCount, other.goalCount) && Objects.equals(goalNo, other.goalNo);
	}

	@Override
	public String toString() {
		return "GoalVO [goalNo=" + goalNo + ", goalCount=" + goalCount + "]";
	}
}
