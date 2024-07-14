package com.ksw.vo.forObject.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public final class AlarmVO implements Serializable {

	private static final long serialVersionUID = 1L;
    private final Integer alarmNo;
    private final String alarmNote;
    private final Integer alarmType;
    private final Boolean isRead;
    private final Timestamp createdAt;

    private AlarmVO(Builder builder) {
        this.alarmNo = builder.alarmNo;
        this.alarmNote = builder.alarmNote;
        this.alarmType = builder.alarmType;
        this.isRead = builder.isRead;
        this.createdAt = builder.createdAt;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlarmVO alarmVO = (AlarmVO) o;
        return Objects.equals(alarmNo, alarmVO.alarmNo) &&
                Objects.equals(alarmNote, alarmVO.alarmNote) &&
                Objects.equals(alarmType, alarmVO.alarmType) &&
                Objects.equals(isRead, alarmVO.isRead) &&
                Objects.equals(createdAt, alarmVO.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alarmNo, alarmNote, alarmType, isRead, createdAt);
    }

    @Override
    public String toString() {
        return "AlarmVO{" +
                "alarmNo=" + alarmNo +
                ", alarmNote='" + alarmNote + '\'' +
                ", alarmType=" + alarmType +
                ", isRead=" + isRead +
                ", createdAt=" + createdAt +
                '}';
    }

    public static class Builder {
        private Integer alarmNo;
        private String alarmNote;
        private Integer alarmType;
        private Boolean isRead;
        private Timestamp createdAt;

        public Builder alarmNo(Integer alarmNo) {
            this.alarmNo = alarmNo;
            return this;
        }

        public Builder alarmNote(String alarmNote) {
            this.alarmNote = alarmNote;
            return this;
        }

        public Builder alarmType(Integer alarmType) {
            this.alarmType = alarmType;
            return this;
        }

        public Builder isRead(Boolean isRead) {
            this.isRead = isRead;
            return this;
        }

        public Builder createdAt(Timestamp createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public AlarmVO build() {
            return new AlarmVO(this);
        }
    }
}
