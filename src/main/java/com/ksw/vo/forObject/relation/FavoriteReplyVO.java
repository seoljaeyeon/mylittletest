package com.ksw.vo.forObject.relation;

import java.io.Serializable;
import java.util.Objects;

import com.ksw.vo.forObject.entity.FavoriteVO;
import com.ksw.vo.forObject.entity.ReplyVO;
import com.ksw.vo.forObject.entity.UserVO;

public final class FavoriteReplyVO implements Serializable {

	private static final long serialVersionUID = 1L;
    private final UserVO userVO;
    private final ReplyVO replyVO;
    private final FavoriteVO favoriteVO;

    private FavoriteReplyVO(Builder builder) {
        this.userVO = builder.userVO;
        this.replyVO = builder.replyVO;
        this.favoriteVO = builder.favoriteVO;
    }

    public UserVO getUserVO() {
		return userVO;
	}

	public ReplyVO getReplyVO() {
		return replyVO;
	}

	public FavoriteVO getFavoriteVO() {
		return favoriteVO;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteReplyVO that = (FavoriteReplyVO) o;
        return Objects.equals(userVO, that.userVO) &&
                Objects.equals(replyVO, that.replyVO) &&
                Objects.equals(favoriteVO, that.favoriteVO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userVO, replyVO, favoriteVO);
    }

    @Override
    public String toString() {
        return "FavoriteReplyVO{" +
                "userVO=" + userVO +
                ", replyVO=" + replyVO +
                ", favoriteVO=" + favoriteVO +
                '}';
    }

    public static class Builder {
        private UserVO userVO;
        private ReplyVO replyVO;
        private FavoriteVO favoriteVO;

        public Builder userVO(UserVO userVO) {
            this.userVO = userVO;
            return this;
        }

        public Builder replyVO(ReplyVO replyVO) {
            this.replyVO = replyVO;
            return this;
        }

        public Builder favoriteVO(FavoriteVO favoriteVO) {
            this.favoriteVO = favoriteVO;
            return this;
        }

        public FavoriteReplyVO build() {
            return new FavoriteReplyVO(this);
        }
    }
}
