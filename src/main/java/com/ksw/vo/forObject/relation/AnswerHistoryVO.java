package com.ksw.vo.forObject.relation;

import java.sql.Timestamp;
import java.util.Objects;

import com.ksw.vo.forObject.entity.AnswerVO;
import com.ksw.vo.forObject.entity.NoteVO;
import com.ksw.vo.forObject.entity.UserVO;

public class AnswerHistoryVO {

    private final NoteVO noteVO;
    private final AnswerVO answerVO;
    private final UserVO userVO;
    private final Timestamp createdAt;
    private final Timestamp updatedAt;

    // 생성자
    public AnswerHistoryVO(NoteVO noteVO, AnswerVO answerVO, UserVO userVO, Timestamp createdAt, Timestamp updatedAt) {
        this.noteVO = noteVO;
        this.answerVO = answerVO;
        this.userVO = userVO;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
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
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
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
                Objects.equals(userVO, that.userVO) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    // hashCode 메소드
    @Override
    public int hashCode() {
        return Objects.hash(noteVO, answerVO, userVO, createdAt, updatedAt);
    }
}