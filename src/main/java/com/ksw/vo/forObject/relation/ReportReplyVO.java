package com.ksw.vo.forObject.relation;

import java.io.Serializable;
import java.util.Objects;

import com.ksw.vo.forObject.entity.ReplyVO;
import com.ksw.vo.forObject.entity.ReportVO;
import com.ksw.vo.forObject.entity.UserVO;

public final class ReportReplyVO implements Serializable {

	private static final long serialVersionUID = 1L;
    private final UserVO userVO;
    private final ReplyVO replyVO;
    private final ReportVO reportVO;

    private ReportReplyVO(Builder builder) {
        this.userVO = builder.userVO;
        this.replyVO = builder.replyVO;
        this.reportVO = builder.reportVO;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportReplyVO that = (ReportReplyVO) o;
        return Objects.equals(userVO, that.userVO) &&
                Objects.equals(replyVO, that.replyVO) &&
                Objects.equals(reportVO, that.reportVO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userVO, replyVO, reportVO);
    }

    @Override
    public String toString() {
        return "ReportReplyVO{" +
                "userVO=" + userVO +
                ", replyVO=" + replyVO +
                ", reportVO=" + reportVO +
                '}';
    }

    public ReportReplyVO(UserVO userVO, ReplyVO replyVO, ReportVO reportVO) {
		super();
		this.userVO = userVO;
		this.replyVO = replyVO;
		this.reportVO = reportVO;
	}

	public static class Builder {
        private UserVO userVO;
        private ReplyVO replyVO;
        private ReportVO reportVO;

        public Builder userVO(UserVO userVO) {
            this.userVO = userVO;
            return this;
        }

        public Builder replyVO(ReplyVO replyVO) {
            this.replyVO = replyVO;
            return this;
        }

        public Builder reportVO(ReportVO reportVO) {
            this.reportVO = reportVO;
            return this;
        }

        public ReportReplyVO build() {
            return new ReportReplyVO(this);
        }
    }
}
