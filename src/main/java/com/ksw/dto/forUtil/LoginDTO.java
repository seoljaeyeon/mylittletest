package com.ksw.dto.forUtil;

public class LoginDTO {
	private String userId;
	private String password;
	
	public LoginDTO() {}
	
	public String getUserId() {
		return userId;
	}
	public String getPassword() {
		return password;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
