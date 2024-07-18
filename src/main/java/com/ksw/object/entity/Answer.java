package com.ksw.object.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "answer")
public class Answer implements Serializable {

	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer answerNo;

    @Column(nullable = false)
    private Integer answerType;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    // basic constructor
    public Answer() {
    	
    }
    
	// 엔티티가 처음 저장되기 전에 실행
    @PrePersist
    protected void onCreate() {
    	createdAt = Timestamp.valueOf(LocalDateTime.now());
    }


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
