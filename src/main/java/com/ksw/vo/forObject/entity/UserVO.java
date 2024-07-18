package com.ksw.vo.forObject.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public final class UserVO implements Serializable {
	
    private static final long serialVersionUID = 1L;
	
    private final Integer userNo;
    private final String userId;
    private final String nickname;
    private final String email;
    private final Boolean isActive;
    private final Integer type;
    private final Timestamp createdAt;
    
    private UserVO(Builder builder) {
        this.userNo = builder.userNo;
        this.userId = builder.userId;
        this.nickname = builder.nickname;
        this.email = builder.email;
        this.isActive = builder.isActive;
        this.type = builder.type;
        this.createdAt = builder.createdAt;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public String getUserId() {
        return userId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public Integer getType() {
        return type;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserVO userVO = (UserVO) o;
        return Objects.equals(userNo, userVO.userNo) &&
                Objects.equals(userId, userVO.userId) &&
                Objects.equals(nickname, userVO.nickname) &&
                Objects.equals(email, userVO.email) &&
                Objects.equals(isActive, userVO.isActive) &&
                Objects.equals(type, userVO.type) &&
                Objects.equals(createdAt, userVO.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userNo, userId, nickname, email, isActive, type, createdAt);
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "userNo=" + userNo +
                ", userId='" + userId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                ", type=" + type +
                ", createdAt=" + createdAt +
                '}';
    }

    public static class Builder {
        private Integer userNo;
        private String userId;
        private String nickname;
        private String email;
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

        public Builder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
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

        public UserVO build() {
            return new UserVO(this);
        }
    }
}
