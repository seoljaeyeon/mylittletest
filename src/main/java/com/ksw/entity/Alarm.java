package com.ksw.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "alarm")
public class Alarm {

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
