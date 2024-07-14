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
@Table(name = "alarm")
public class Alarm implements Serializable{

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer alarmNo;

    @Column(nullable = false, length = 255)
    private String alarmNote;

    @Column(nullable = false)
    private Integer alarmType;

    @Column(columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean isRead = false;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;
    
    // 기본 생성자
	public Alarm() {
		super();
	}
	
	// 엔티티가 처음 저장되기 전에 실행
    @PrePersist
    protected void onCreate() {
    	createdAt = Timestamp.valueOf(LocalDateTime.now());
    }


	public Integer getAlarmNo() {
		return alarmNo;
	}

	public String getAlarmNote() {
		return alarmNote;
	}

	public Integer getAlarmType() {
		return alarmType;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setAlarmNo(Integer alarmNo) {
		this.alarmNo = alarmNo;
	}

	public void setAlarmNote(String alarmNote) {
		this.alarmNote = alarmNote;
	}

	public void setAlarmType(Integer alarmType) {
		this.alarmType = alarmType;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}
