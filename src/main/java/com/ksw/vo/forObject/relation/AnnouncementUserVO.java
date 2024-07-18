package com.ksw.vo.forObject.relation;

import java.io.Serializable;
import java.util.Objects;

import com.ksw.vo.forObject.entity.AnnouncementVO;
import com.ksw.vo.forObject.entity.UserVO;

public class AnnouncementUserVO implements Serializable {

	private static final long serialVersionUID = 1L;

    private final AnnouncementVO announcementVO;
    private final UserVO userVO;

    // 생성자
    public AnnouncementUserVO(AnnouncementVO announcementVO, UserVO userVO) {
        this.announcementVO = announcementVO;
        this.userVO = userVO;
    }

    // Getters
    public AnnouncementVO getAnnouncementVO() {
        return announcementVO;
    }

    public UserVO getUserVO() {
        return userVO;
    }

    // 빌더 패턴 구현
    public static class Builder {
        private AnnouncementVO announcementVO;
        private UserVO userVO;

        public Builder announcementVO(AnnouncementVO announcementVO) {
            this.announcementVO = announcementVO;
            return this;
        }

        public Builder userVO(UserVO userVO) {
            this.userVO = userVO;
            return this;
        }

        public AnnouncementUserVO build() {
            return new AnnouncementUserVO(announcementVO, userVO);
        }
    }

    // toString 메소드
    @Override
    public String toString() {
        return "AnnouncementUserVO{" +
                "announcementVO=" + announcementVO +
                ", userVO=" + userVO +
                '}';
    }

    // equals 메소드
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnnouncementUserVO that = (AnnouncementUserVO) o;
        return Objects.equals(announcementVO, that.announcementVO) &&
                Objects.equals(userVO, that.userVO);
    }

    // hashCode 메소드
    @Override
    public int hashCode() {
        return Objects.hash(announcementVO, userVO);
    }
}