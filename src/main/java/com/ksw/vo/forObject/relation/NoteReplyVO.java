package com.ksw.vo.forObject.relation;

import java.io.Serializable;
import java.util.Objects;

import com.ksw.vo.forObject.entity.NoteVO;
import com.ksw.vo.forObject.entity.ReplyVO;

public final class NoteReplyVO implements Serializable {

	private static final long serialVersionUID = 1L;
    private final NoteVO noteVO;
    private final ReplyVO replyVO;

    private NoteReplyVO(Builder builder) {
        this.noteVO = builder.noteVO;
        this.replyVO = builder.replyVO;
    }


    public NoteReplyVO(NoteVO noteVO, ReplyVO replyVO) {
		super();
		this.noteVO = noteVO;
		this.replyVO = replyVO;
	}


	public NoteVO getNoteVO() {
		return noteVO;
	}


	public ReplyVO getReplyVO() {
		return replyVO;
	}


	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteReplyVO that = (NoteReplyVO) o;
        return Objects.equals(noteVO, that.noteVO) &&
                Objects.equals(replyVO, that.replyVO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteVO, replyVO);
    }

    @Override
    public String toString() {
        return "NoteReplyVO{" +
                "noteVO=" + noteVO +
                ", replyVO=" + replyVO +
                '}';
    }

    public static class Builder {
        private NoteVO noteVO;
        private ReplyVO replyVO;

        public Builder noteVO(NoteVO noteVO) {
            this.noteVO = noteVO;
            return this;
        }

        public Builder replyVO(ReplyVO replyVO) {
            this.replyVO = replyVO;
            return this;
        }

        public NoteReplyVO build() {
            return new NoteReplyVO(this);
        }
    }
}
