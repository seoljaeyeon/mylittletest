package com.ksw.vo.forObject.relation;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import com.ksw.vo.forObject.entity.AnswerVO;
import com.ksw.vo.forObject.entity.NoteVO;
import com.ksw.vo.forObject.entity.UserVO;

public class AnswerHistoryVO implements Serializable {

	private static final long serialVersionUID = 1L;

    private final NoteVO noteVO;
    private final AnswerVO answerVO;
    private final UserVO userVO;

    // 생성자
    public AnswerHistoryVO(NoteVO noteVO, AnswerVO answerVO, UserVO userVO, Timestamp createdAt, Timestamp updatedAt) {
        this.noteVO = noteVO;
        this.answerVO = answerVO;
        this.userVO = userVO;
    }

    // Getters
    public NoteVO getNoteVO() {
        return noteVO;
    }

    public AnswerVO getAnswerVO() {
        return answerVO;
    }

    public UserVO getUserVO() {
        return userVO;
    }


    // 빌더 패턴 구현
    public static class Builder {
        private NoteVO noteVO;
        private AnswerVO answerVO;
        private UserVO userVO;
        private Timestamp createdAt;
        private Timestamp updatedAt;

        public Builder noteVO(NoteVO noteVO) {
            this.noteVO = noteVO;
            return this;
        }

        public Builder answerVO(AnswerVO answerVO) {
            this.answerVO = answerVO;
            return this;
        }

        public Builder userVO(UserVO userVO) {
            this.userVO = userVO;
            return this;
        }

        public Builder createdAt(Timestamp createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(Timestamp updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public AnswerHistoryVO build() {
            return new AnswerHistoryVO(noteVO, answerVO, userVO, createdAt, updatedAt);
        }
    }

    // toString 메소드
    @Override
    public String toString() {
        return "AnswerHistoryVO{" +
                "noteVO=" + noteVO +
                ", answerVO=" + answerVO +
                ", userVO=" + userVO +
                '}';
    }

    // equals 메소드
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerHistoryVO that = (AnswerHistoryVO) o;
        return Objects.equals(noteVO, that.noteVO) &&
                Objects.equals(answerVO, that.answerVO) &&
                Objects.equals(userVO, that.userVO);
    }

    // hashCode 메소드
    @Override
    public int hashCode() {
        return Objects.hash(noteVO, answerVO, userVO);
    }
}