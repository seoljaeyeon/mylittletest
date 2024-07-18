package com.ksw.dto.forObject.relation;

import java.io.Serializable;

import com.ksw.dto.forObject.entity.UserDTO;

public class ReportManagementDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	

    private UserDTO userDTO;
    private UserDTO managerDTO;
    private UserDTO solverDTO;

    public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public UserDTO getManagerDTO() {
		return managerDTO;
	}

	public void setManagerDTO(UserDTO managerDTO) {
		this.managerDTO = managerDTO;
	}

	public UserDTO getSolverDTO() {
		return solverDTO;
	}

	public void setSolverDTO(UserDTO solverDTO) {
		this.solverDTO = solverDTO;
	}

	// 기본 생성자
    public ReportManagementDTO() {}

}
