package com.ksw.vo.forObject.relation;

import java.io.Serializable;
import java.util.Objects;

import com.ksw.vo.forObject.entity.AlarmVO;
import com.ksw.vo.forObject.entity.NoteVO;
import com.ksw.vo.forObject.entity.ReplyVO;
import com.ksw.vo.forObject.entity.UserVO;

public class AlarmRelationVO implements Serializable {

	private static final long serialVersionUID = 1L;

    private final AlarmVO alarmVO;
    private final UserVO receiverVO;
    private final UserVO makerVO;
    private final NoteVO noteVO;
    private final ReplyVO replyVO;

    // 생성자
    public AlarmRelationVO(Builder builder) {
    	this.alarmVO = builder.alarmVO;
    	this.receiverVO = builder.receiverVO;
    	this.makerVO = builder.makerVO;
    	this.noteVO = builder.noteVO;
    	this.replyVO = builder.replyVO;
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

    public NoteVO getNoteVO() {
		return noteVO;
	}

	public ReplyVO getReplyVO() {
		return replyVO;
	}

	// 빌더 패턴 구현
    public static class Builder {
        private AlarmVO alarmVO;
        private UserVO receiverVO;
        private UserVO makerVO;
        private NoteVO noteVO;
        private ReplyVO replyVO;

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
        public Builder noteVO(NoteVO noteVO) {
            this.noteVO = noteVO;
            return this;
        }
        public Builder replyVO(ReplyVO replyVO) {
            this.replyVO = replyVO;
            return this;
        }
        

        public AlarmRelationVO build() {
            return new AlarmRelationVO(this);
        }
    }

	@Override
	public int hashCode() {
		return Objects.hash(alarmVO, makerVO, noteVO, receiverVO, replyVO);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlarmRelationVO other = (AlarmRelationVO) obj;
		return Objects.equals(alarmVO, other.alarmVO) && Objects.equals(makerVO, other.makerVO)
				&& Objects.equals(noteVO, other.noteVO) && Objects.equals(receiverVO, other.receiverVO)
				&& Objects.equals(replyVO, other.replyVO);
	}

	@Override
	public String toString() {
		return "AlarmRelationVO [alarmVO=" + alarmVO + ", receiverVO=" + receiverVO + ", makerVO=" + makerVO
				+ ", noteVO=" + noteVO + ", replyVO=" + replyVO + "]";
	}
}