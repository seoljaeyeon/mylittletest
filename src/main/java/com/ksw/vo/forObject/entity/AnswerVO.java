package com.ksw.vo.forObject.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public final class AnswerVO implements Serializable {

	private static final long serialVersionUID = 1L;
    private final Integer answerNo;
    private final Integer answerType;
    private final Timestamp createdAt;

    private AnswerVO(Builder builder) {
        this.answerNo = builder.answerNo;
        this.answerType = builder.answerType;
        this.createdAt = builder.createdAt;
    }

    public Integer getAnswerNo() {
        return answerNo;
    }

    public Integer getAnswerType() {
        return answerType;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerVO answerVO = (AnswerVO) o;
        return Objects.equals(answerNo, answerVO.answerNo) &&
                Objects.equals(answerType, answerVO.answerType) &&
                Objects.equals(createdAt, answerVO.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answerNo, answerType, createdAt);
    }

    @Override
    public String toString() {
        return "AnswerVO{" +
                "answerNo=" + answerNo +
                ", answerType=" + answerType +
                ", createdAt=" + createdAt +
                '}';
    }

    public static class Builder {
        private Integer answerNo;
        private Integer answerType;
        private Timestamp createdAt;

        public Builder answerNo(Integer answerNo) {
            this.answerNo = answerNo;
            return this;
        }

        public Builder answerType(Integer answerType) {
            this.answerType = answerType;
            return this;
        }

        public Builder createdAt(Timestamp createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public AnswerVO build() {
            return new AnswerVO(this);
        }
    }
}
