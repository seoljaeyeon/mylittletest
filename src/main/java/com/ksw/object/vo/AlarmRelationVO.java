package com.ksw.object.vo;

import java.util.Objects;

public final class AlarmRelationVO {
    private final Integer alarmNo;
    private final Integer userNo;
    private final Integer makerNo;

    private AlarmRelationVO(Builder builder) {
        this.alarmNo = builder.alarmNo;
        this.userNo = builder.userNo;
        this.makerNo = builder.makerNo;
    }

    public Integer getAlarmNo() {
        return alarmNo;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public Integer getMakerNo() {
        return makerNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlarmRelationVO that = (AlarmRelationVO) o;
        return Objects.equals(alarmNo, that.alarmNo) &&
                Objects.equals(userNo, that.userNo) &&
                Objects.equals(makerNo, that.makerNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alarmNo, userNo, makerNo);
    }

    @Override
    public String toString() {
        return "AlarmRelationVO{" +
                "alarmNo=" + alarmNo +
                ", userNo=" + userNo +
                ", makerNo=" + makerNo +
                '}';
    }

    public static class Builder {
        private Integer alarmNo;
        private Integer userNo;
        private Integer makerNo;

        public Builder alarmNo(Integer alarmNo) {
            this.alarmNo = alarmNo;
            return this;
        }

        public Builder userNo(Integer userNo) {
            this.userNo = userNo;
            return this;
        }

        public Builder makerNo(Integer makerNo) {
            this.makerNo = makerNo;
            return this;
        }

        public AlarmRelationVO build() {
            return new AlarmRelationVO(this);
        }
    }
}
