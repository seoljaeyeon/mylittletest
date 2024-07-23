package com.ksw.vo.forObject.relation;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import com.ksw.vo.forObject.entity.GoalVO;
import com.ksw.vo.forObject.entity.UserVO;

public class UserGoalVO implements Serializable{

	private static final long	serialVersionUID = 1L;
	
	private UserVO userVO;
	private GoalVO goalVO;
	private Timestamp createdAt;
	
	public UserVO getUserVO() {
		return userVO;
	}
	
	public GoalVO getGoal() {	
		return goalVO;
	}
	
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	
	private UserGoalVO(Builder builder) {
		this.userVO = builder.userVO;
		this.goalVO = builder.goalVO;
		this.createdAt = builder.createdAt;
	}
	
	public static class Builder {
		private UserVO userVO;
		private GoalVO goalVO;
		private Timestamp createdAt;
		
		public Builder userVO(UserVO userVO) {
			this.userVO=userVO;
			return this;
		}
		
		public Builder goalVO(GoalVO goalVO) {
			this.goalVO = goalVO;
			return this;
		}
		
		public Builder createdAt(Timestamp createdAt) {
			this.createdAt = createdAt;
			return this;
		}
		
		public UserGoalVO build() {
			return new UserGoalVO(this);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(createdAt, goalVO, userVO);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserGoalVO other = (UserGoalVO) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(goalVO, other.goalVO)
				&& Objects.equals(userVO, other.userVO);
	}

	@Override
	public String toString() {
		return "UserGoalVO [userVO=" + userVO + ", goalVO=" + goalVO + ", createdAt=" + createdAt + "]";
	}

	
	
	
	
}
