package com.ksw.dto.forObject.relation;

public class AlarmRelationDTO {

    private Integer alarmNo;
    private Integer userNo;
    private Integer makerNo;
    private Integer noteNo;
    private Integer replyNo;
    private Integer cateogryNo;

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
    

    public Integer getNoteNo() {
		return noteNo;
	}

	public Integer getReplyNo() {
		return replyNo;
	}

	public Integer getCateogryNo() {
		return cateogryNo;
	}

	public void setNoteNo(Integer noteNo) {
		this.noteNo = noteNo;
	}

	public void setReplyNo(Integer replyNo) {
		this.replyNo = replyNo;
	}

	public void setCateogryNo(Integer cateogryNo) {
		this.cateogryNo = cateogryNo;
	}



	// 빌더 패턴 구현
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

        public AlarmRelationDTO build() {
            AlarmRelationDTO alarmRelationDTO = new AlarmRelationDTO();
            alarmRelationDTO.alarmNo = this.alarmNo;
            alarmRelationDTO.userNo = this.userNo;
            alarmRelationDTO.makerNo = this.makerNo;
            alarmRelationDTO.noteNo = this.noteNo;
            alarmRelationDTO.replyNo = this.replyNo;
            alarmRelationDTO.cateogryNo = this.cateogryNo;
            return alarmRelationDTO;
        }
    }
}