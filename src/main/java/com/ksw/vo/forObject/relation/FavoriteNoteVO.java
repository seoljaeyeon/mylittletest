package com.ksw.vo.forObject.relation;

import java.io.Serializable;
import java.util.Objects;

import com.ksw.vo.forObject.entity.FavoriteVO;
import com.ksw.vo.forObject.entity.NoteVO;
import com.ksw.vo.forObject.entity.UserVO;

public final class FavoriteNoteVO implements Serializable {

	private static final long serialVersionUID = 1L;
    private final UserVO userVO;
    private final NoteVO noteVO;
    private final FavoriteVO favoriteVO;

    private FavoriteNoteVO(Builder builder) {
        this.userVO = builder.userVO;
        this.noteVO = builder.noteVO;
        this.favoriteVO = builder.favoriteVO;
    }

    public UserVO getUserVO() {
		return userVO;
	}

	public NoteVO getNoteVO() {
		return noteVO;
	}

	public FavoriteVO getFavoriteVO() {
		return favoriteVO;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteNoteVO that = (FavoriteNoteVO) o;
        return Objects.equals(userVO, that.userVO) &&
                Objects.equals(noteVO, that.noteVO) &&
                Objects.equals(favoriteVO, that.favoriteVO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userVO, noteVO, favoriteVO);
    }

    @Override
    public String toString() {
        return "FavoriteNoteVO{" +
                "userVO=" + userVO +
                ", noteVO=" + noteVO +
                ", favoriteVO=" + favoriteVO +
                '}';
    }

    public static class Builder {
        private UserVO userVO;
        private NoteVO noteVO;
        private FavoriteVO favoriteVO;

        public Builder userVO(UserVO userVO) {
            this.userVO = userVO;
            return this;
        }

        public Builder noteVO(NoteVO noteVO) {
            this.noteVO = noteVO;
            return this;
        }

        public Builder favoriteVO(FavoriteVO favoriteVO) {
            this.favoriteVO = favoriteVO;
            return this;
        }

        public FavoriteNoteVO build() {
            return new FavoriteNoteVO(this);
        }
    }
}
