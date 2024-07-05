package com.ksw.dto.forObject.object;

import java.sql.Timestamp;

public class AnswerDTO {
    private Integer answerNo;
    private Integer answerType;
    private Timestamp createdAt;

    // Getters and Setters
    public Integer getAnswerNo() {
        return answerNo;
    }

    public void setAnswerNo(Integer answerNo) {
        this.answerNo = answerNo;
    }

    public Integer getAnswerType() {
        return answerType;
    }

    public void setAnswerType(Integer answerType) {
        this.answerType = answerType;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
