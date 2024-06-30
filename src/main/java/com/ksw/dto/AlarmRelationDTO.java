package com.ksw.dto;

public class AlarmRelationDTO {

    private Integer alarmNo;
    private Integer userNo;
    private Integer makerNo;

    // 기본 생성자
    public AlarmRelationDTO() {}

    // Getter 및 Setter
    public Integer getAlarmNo() {
        return alarmNo;
    }

    public void setAlarmNo(Integer alarmNo) {
        this.alarmNo = alarmNo;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public Integer getMakerNo() {
        return makerNo;
    }

    public void setMakerNo(Integer makerNo) {
        this.makerNo = makerNo;
    }

    // 빌더 패턴 구현
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

        public AlarmRelationDTO build() {
            AlarmRelationDTO alarmRelationDTO = new AlarmRelationDTO();
            alarmRelationDTO.alarmNo = this.alarmNo;
            alarmRelationDTO.userNo = this.userNo;
            alarmRelationDTO.makerNo = this.makerNo;
            return alarmRelationDTO;
        }
    }
}