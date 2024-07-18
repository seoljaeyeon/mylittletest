package com.ksw.dto.forObject.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class AlarmDTO implements Serializable
{

	private static final long serialVersionUID = 1L;
	
    private Integer alarmNo;
    private String alarmNote;
    private Integer alarmType;
    private Boolean isRead;
    private Timestamp createdAt;

    // 기본 생성자
    public AlarmDTO() {}

    // Getters and Setters
    public Integer getAlarmNo() {
        return alarmNo;
    }

    public void setAlarmNo(Integer alarmNo) {
        this.alarmNo = alarmNo;
    }

    public String getAlarmNote() {
        return alarmNote;
    }

    public void setAlarmNote(String alarmNote) {
        this.alarmNote = alarmNote;
    }

    public Integer getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(Integer alarmType) {
        this.alarmType = alarmType;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
