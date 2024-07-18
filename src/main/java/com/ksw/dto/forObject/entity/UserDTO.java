package com.ksw.dto.forObject.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	

    private Integer userNo;
    private String userId;
    private String password;
    private String nickname;
    private String email;
    private Integer securityQuestion;
    private String securityAnswer;
    private Boolean isActive;
    private Integer type;
    private Timestamp createdAt;

    // 기본 생성자
    public UserDTO() {}

    // Getters and Setters
    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
