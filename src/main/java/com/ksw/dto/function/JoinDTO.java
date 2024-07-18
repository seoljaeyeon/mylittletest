package com.ksw.dto.function;

import java.io.Serializable;

public class JoinDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
    private String userId;
    private String password;
    private String nickname;
    private String email;
    private Integer securityQuestion;
    private String securityAnswer;
    private String code;

    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	// 기본 생성자
    public JoinDTO() {}

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(Integer securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }
}
