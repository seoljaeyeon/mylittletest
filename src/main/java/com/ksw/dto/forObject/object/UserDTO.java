package com.ksw.dto.forObject.object;

import java.sql.Timestamp;

public class UserDTO {

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
    // 빌더 패턴 구현
    public static class Builder {
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

        public Builder userNo(Integer userNo) {
            this.userNo = userNo;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder securityQuestion(Integer securityQuestion) {
            this.securityQuestion = securityQuestion;
            return this;
        }

        public Builder securityAnswer(String securityAnswer) {
            this.securityAnswer = securityAnswer;
            return this;
        }

        public Builder isActive(Boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public Builder type(Integer type) {
            this.type = type;
            return this;
        }

        public Builder createdAt(Timestamp createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserDTO build() {
            UserDTO userDTO = new UserDTO();
            userDTO.userNo = this.userNo;
            userDTO.userId = this.userId;
            userDTO.password = this.password;
            userDTO.nickname = this.nickname;
            userDTO.email = this.email;
            userDTO.securityQuestion = this.securityQuestion;
            userDTO.securityAnswer = this.securityAnswer;
            userDTO.isActive = this.isActive;
            userDTO.type = this.type;
            userDTO.createdAt = this.createdAt;
            return userDTO;
        }
    }
}
