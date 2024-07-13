package com.ksw.vo.forObject.relation;

import java.io.Serializable;
import java.util.Objects;

import com.ksw.vo.forObject.entity.NoteVO;
import com.ksw.vo.forObject.entity.ReportVO;
import com.ksw.vo.forObject.entity.UserVO;

public final class ReportNoteVO implements Serializable {

	private static final long serialVersionUID = 1L;
    private final UserVO userVO;
    private final NoteVO noteVO;
    private final ReportVO reportVO;

    private ReportNoteVO(Builder builder) {
        this.userVO = builder.userVO;
        this.noteVO = builder.noteVO;
        this.reportVO = builder.reportVO;
    }

    public UserVO getUserVO() {
		return userVO;
	}

	public NoteVO getNoteVO() {
		return noteVO;
	}

	public ReportVO getReportVO() {
		return reportVO;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportNoteVO that = (ReportNoteVO) o;
        return Objects.equals(userVO, that.userVO) &&
                Objects.equals(noteVO, that.noteVO) &&
                Objects.equals(reportVO, that.reportVO);
    }

    public ReportNoteVO(UserVO userVO, NoteVO noteVO, ReportVO reportVO) {
		super();
		this.userVO = userVO;
		this.noteVO = noteVO;
		this.reportVO = reportVO;
	}

	@Override
    public int hashCode() {
        return Objects.hash(userVO, noteVO, reportVO);
    }

    @Override
    public String toString() {
        return "ReportNoteVO{" +
                "userVO=" + userVO +
                ", noteVO=" + noteVO +
                ", reportVO=" + reportVO +
                '}';
    }

    public static class Builder {
        private UserVO userVO;
        private NoteVO noteVO;
        private ReportVO reportVO;

        public Builder userVO(UserVO userVO) {
            this.userVO = userVO;
            return this;
        }

        public Builder noteVO(NoteVO noteVO) {
            this.noteVO = noteVO;
            return this;
        }

        public Builder reportVO(ReportVO reportVO) {
            this.reportVO = reportVO;
            return this;
        }

        public ReportNoteVO build() {
            return new ReportNoteVO(this);
        }
    }
}
