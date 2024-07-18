package com.ksw.vo.forObject.relation;

import java.io.Serializable;
import java.util.Objects;

import com.ksw.vo.forObject.entity.NoteVO;
import com.ksw.vo.forObject.entity.UserVO;

public final class NoteUserVO implements Serializable {

	private static final long serialVersionUID = 1L;
    private final UserVO userVO;
    private final NoteVO noteVO;

    private NoteUserVO(Builder builder) {
        this.userVO = builder.userVO;
        this.noteVO = builder.noteVO;
    }


    public NoteUserVO(UserVO userVO, NoteVO noteVO) {
		super();
		this.userVO = userVO;
		this.noteVO = noteVO;
	}


	public UserVO getUserVO() {
		return userVO;
	}


	public NoteVO getNoteVO() {
		return noteVO;
	}


	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteUserVO that = (NoteUserVO) o;
        return Objects.equals(userVO, that.userVO) &&
                Objects.equals(noteVO, that.noteVO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userVO, noteVO);
    }

    @Override
    public String toString() {
        return "NoteUserVO{" +
                "userVO=" + userVO +
                ", noteVO=" + noteVO +
                '}';
    }

    public static class Builder {
        private UserVO userVO;
        private NoteVO noteVO;

        public Builder userVO(UserVO userVO) {
            this.userVO = userVO;
            return this;
        }

        public Builder noteVO(NoteVO noteVO) {
            this.noteVO = noteVO;
            return this;
        }

        public NoteUserVO build() {
            return new NoteUserVO(this);
        }
    }
}
