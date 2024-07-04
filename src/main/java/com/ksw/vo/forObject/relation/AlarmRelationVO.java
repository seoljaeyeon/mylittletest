package com.ksw.vo.forObject.relation;

import java.util.Objects;

public final class AlarmRelationVO {
    private final Integer alarmNo;
    private final Integer userNo;
    private final Integer makerNo;
    private final Integer noteNo;
    private final Integer replyNo;
    private final Integer cateogryNo;

    private AlarmRelationVO(Builder builder) {
        this.alarmNo = builder.alarmNo;
        this.userNo = builder.userNo;
        this.makerNo = builder.makerNo;
        this.noteNo = builder.noteNo;
        this.replyNo = builder.replyNo;
        this.cateogryNo = builder.cateogryNo;

        
    }

    public Integer getNoteNo() {
		return noteNo;
	}

	public Integer getReplyNo() {
		return replyNo;
	}

	public Integer getCateogryNo() {
		return cateogryNo;
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
                Objects.equals(makerNo, that.makerNo) &&
                Objects.equals(noteNo, that.noteNo) &&
                Objects.equals(replyNo, that.replyNo) &&
                Objects.equals(cateogryNo, that.cateogryNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alarmNo, userNo, makerNo, noteNo, replyNo, cateogryNo);
    }

    @Override
    public String toString() {
        return "AlarmRelationVO{" +
                "alarmNo=" + alarmNo +
                ", userNo=" + userNo +
                ", makerNo=" + makerNo +
                ", noteNo=" + noteNo +
                ", replyNo=" + replyNo +
                ", cateogryNo=" +cateogryNo +
                '}';
    }

    public static class Builder {
        private Integer alarmNo;
        private Integer userNo;
        private Integer makerNo;
        private Integer noteNo;
        private Integer replyNo;
        private Integer cateogryNo;

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
        
        public Builder noteNo(Integer noteNo) {
            this.noteNo = noteNo;
            return this;
        }
        public Builder replyNo(Integer replyNo) {
            this.replyNo = replyNo;
            return this;
        }
        public Builder cateogryNo(Integer cateogryNo) {
            this.cateogryNo = cateogryNo;
            return this;
        }
        

        public AlarmRelationVO build() {
            return new AlarmRelationVO(this);
        }
    }
}
