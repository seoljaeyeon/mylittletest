package com.ksw.dto.forObject.relation;

import java.io.Serializable;
import java.sql.Timestamp;

import com.ksw.dto.forObject.entity.GoalDTO;
import com.ksw.dto.forObject.entity.UserDTO;

public class UserGoalDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private UserDTO userDTO;
	private GoalDTO goalDTO;
	private Timestamp createdAt;
	public UserDTO getUserDTO() {
		return userDTO;
	}
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	public GoalDTO getGoalDTO() {
		return goalDTO;
	}
	public void setGoalDTO(GoalDTO goalDTO) {
		this.goalDTO = goalDTO;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
}
