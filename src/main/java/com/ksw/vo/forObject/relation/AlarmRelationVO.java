package com.ksw.vo.forObject.relation;

import java.io.Serializable;
import java.util.Objects;

import com.ksw.vo.forObject.entity.AlarmVO;
import com.ksw.vo.forObject.entity.UserVO;

public class AlarmRelationVO implements Serializable {

	private static final long serialVersionUID = 1L;

    private final AlarmVO alarmVO;
    private final UserVO receiverVO;
    private final UserVO makerVO;

    // 생성자
    public AlarmRelationVO(AlarmVO alarmVO, UserVO receiverVO, UserVO makerVO) {
        this.alarmVO = alarmVO;
        this.receiverVO = receiverVO;
        this.makerVO = makerVO;
    }

    // Getters
    public AlarmVO getAlarmVO() {
        return alarmVO;
    }

    public UserVO getReceiverVO() {
        return receiverVO;
    }

    public UserVO getMakerVO() {
        return makerVO;
    }

    // 빌더 패턴 구현
    public static class Builder {
        private AlarmVO alarmVO;
        private UserVO receiverVO;
        private UserVO makerVO;

        public Builder alarmVO(AlarmVO alarmVO) {
            this.alarmVO = alarmVO;
            return this;
        }

        public Builder receiverVO(UserVO receiverVO) {
            this.receiverVO = receiverVO;
            return this;
        }

        public Builder makerVO(UserVO makerVO) {
            this.makerVO = makerVO;
            return this;
        }

        public AlarmRelationVO build() {
            return new AlarmRelationVO(alarmVO, receiverVO, makerVO);
        }
    }

    // toString 메소드
    @Override
    public String toString() {
        return "AlarmRelationVO{" +
                "alarmVO=" + alarmVO +
                ", receiverVO=" + receiverVO +
                ", makerVO=" + makerVO +
                '}';
    }

    // equals 메소드
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlarmRelationVO that = (AlarmRelationVO) o;
        return Objects.equals(alarmVO, that.alarmVO) &&
                Objects.equals(receiverVO, that.receiverVO) &&
                Objects.equals(makerVO, that.makerVO);
    }

    // hashCode 메소드
    @Override
    public int hashCode() {
        return Objects.hash(alarmVO, receiverVO, makerVO);
    }
}