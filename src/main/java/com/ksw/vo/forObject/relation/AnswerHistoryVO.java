package com.ksw.vo.forObject.relation;

import java.sql.Timestamp;
import java.util.Objects;

public final class AnswerHistoryVO {
    private final Integer noteNo;
    private final Integer answerNo;
    private final Integer userNo;
    private final Timestamp createdAt;
    private final Timestamp updatedAt;

    private AnswerHistoryVO(Builder builder) {
        this.noteNo = builder.noteNo;
        this.answerNo = builder.answerNo;
        this.userNo = builder.userNo;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;

    }


    public Integer getNoteNo() {
        return noteNo;
    }

    public Integer getAnswerNo() {
        return answerNo;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
    
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerHistoryVO that = (AnswerHistoryVO) o;
        return 	Objects.equals(noteNo, that.noteNo) &&
                Objects.equals(answerNo, that.answerNo) &&
                Objects.equals(userNo, that.userNo) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteNo, answerNo, userNo, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "AnswerHistoryVO{" +
                "noteNo=" + noteNo +
                ", answerNo=" + answerNo +
                ", userNo=" + userNo +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public static class Builder {
        private Integer noteNo;
        private Integer answerNo;
        private Integer userNo;
        private Timestamp createdAt;
        private Timestamp updatedAt;


        public Builder noteNo(Integer noteNo) {
            this.noteNo = noteNo;
            return this;
        }

        public Builder answerNo(Integer answerNo) {
            this.answerNo = answerNo;
            return this;
        }

        public Builder userNo(Integer userNo) {
            this.userNo = userNo;
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
            return new AnswerHistoryVO(this);
        }
    }
}
