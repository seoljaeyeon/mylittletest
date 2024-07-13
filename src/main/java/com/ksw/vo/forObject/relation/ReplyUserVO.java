package com.ksw.vo.forObject.relation;

import java.io.Serializable;
import java.util.Objects;

import com.ksw.vo.forObject.entity.ReplyVO;
import com.ksw.vo.forObject.entity.UserVO;

public final class ReplyUserVO implements Serializable {

	private static final long serialVersionUID = 1L;
    private final UserVO userVO;
    private final ReplyVO replyVO;

    private ReplyUserVO(Builder builder) {
        this.userVO = builder.userVO;
        this.replyVO = builder.replyVO;
    }

    public ReplyUserVO(UserVO userVO, ReplyVO replyVO) {
		super();
		this.userVO = userVO;
		this.replyVO = replyVO;
	}

	public UserVO getUserVO() {
		return userVO;
	}

	public ReplyVO getReplyVO() {
		return replyVO;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReplyUserVO that = (ReplyUserVO) o;
        return Objects.equals(userVO, that.userVO) &&
                Objects.equals(replyVO, that.replyVO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userVO, replyVO);
    }

    @Override
    public String toString() {
        return "ReplyUserVO{" +
                "userVO=" + userVO +
                ", replyVO=" + replyVO +
                '}';
    }

    public static class Builder {
        private UserVO userVO;
        private ReplyVO replyVO;

        public Builder userVO(UserVO userVO) {
            this.userVO = userVO;
            return this;
        }

        public Builder replyVO(ReplyVO replyVO) {
            this.replyVO = replyVO;
            return this;
        }

        public ReplyUserVO build() {
            return new ReplyUserVO(this);
        }
    }
}
