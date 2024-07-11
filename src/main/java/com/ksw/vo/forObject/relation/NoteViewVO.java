package com.ksw.vo.forObject.relation;


import java.util.Objects;

import com.ksw.vo.forObject.entity.NoteVO;
import com.ksw.vo.forObject.entity.UserVO;
import com.ksw.vo.forObject.entity.ViewVO;

public class NoteViewVO {

    private final NoteVO noteVO;
    private final ViewVO viewVO;
    private final UserVO userVO;

    // 생성자
    public NoteViewVO(NoteVO noteVO, ViewVO viewVO, UserVO userVO) {
        this.noteVO = noteVO;
        this.viewVO = viewVO;
        this.userVO = userVO;
    }

    // Getters
    public NoteVO getnoteVO() {
        return noteVO;
    }

    public ViewVO getViewVO() {
        return viewVO;
    }

    // 빌더 패턴 구현
    public static class Builder {
        private NoteVO noteVO;
        private ViewVO viewVO;
        private UserVO userVO;

        public Builder noteVO(NoteVO noteVO) {
            this.noteVO = noteVO;
            return this;
        }

        public Builder viewVO(ViewVO viewVO) {
            this.viewVO = viewVO;
            return this;
        }
        
        public Builder userVO(UserVO userVO) {
            this.userVO = userVO;
            return this;
        }

        public NoteViewVO build() {
            return new NoteViewVO(noteVO, viewVO, userVO);
        }
    }

    // toString 메소드
    @Override
    public String toString() {
        return "CategoryViewVO{" +
                "noteVO=" + noteVO +
                ", viewVO=" + viewVO +
                ", userVO=" + userVO +
                '}';
    }

    // equals 메소드
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteViewVO that = (NoteViewVO) o;
        return Objects.equals(noteVO, that.noteVO) &&
                Objects.equals(viewVO, that.viewVO) &&
                Objects.equals(userVO, that.userVO);
    }

    // hashCode 메소드
    @Override
    public int hashCode() {
        return Objects.hash(noteVO, viewVO, userVO);
    }
}
