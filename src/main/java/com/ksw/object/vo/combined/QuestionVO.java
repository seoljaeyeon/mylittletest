package com.ksw.object.vo.combined;

import java.util.List;
import java.util.Objects;

import com.ksw.object.vo.object.CategoryVO;
import com.ksw.object.vo.object.FileVO;
import com.ksw.object.vo.object.NoteVO;
import com.ksw.object.vo.object.ReplyVO;
import com.ksw.object.vo.object.UserVO;

public final class QuestionVO {
    private final NoteVO noteVO;
    private final UserVO userVO;
    private final CategoryVO categoryVO;
    private final FileVO fileVO;
    private final List<ReplyVO> replies;
    private final int viewCount;
    private final int favoriteCount;

    private QuestionVO(Builder builder) {
        this.noteVO = builder.noteVO;
        this.userVO = builder.userVO;
        this.categoryVO = builder.categoryVO;
        this.fileVO = builder.fileVO;
        this.replies = builder.replies;
        this.viewCount = builder.viewCount;
        this.favoriteCount = builder.favoriteCount;
    }

    public NoteVO getNoteVO() {
        return noteVO;
    }

    public UserVO getUserVO() {
        return userVO;
    }

    public CategoryVO getCategoryVO() {
        return categoryVO;
    }

    public FileVO getFileVO() {
        return fileVO;
    }

    public List<ReplyVO> getReplies() {
		return replies;
	}

	public int getViewCount() {
		return viewCount;
	}

	public int getFavoriteCount() {
		return favoriteCount;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionVO that = (QuestionVO) o;
        return Objects.equals(noteVO, that.noteVO) &&
                Objects.equals(userVO, that.userVO) &&
                Objects.equals(categoryVO, that.categoryVO) &&
                Objects.equals(fileVO, that.fileVO) &&
                Objects.equals(replies, that.replies) &&
        		Objects.equals(viewCount, that.viewCount) &&
        		Objects.equals(favoriteCount, that.favoriteCount);
        
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteVO, userVO, categoryVO, fileVO, replies, viewCount, favoriteCount);
    }

    @Override
    public String toString() {
        return "WriteVO{" +
                "noteVO=" + noteVO +
                ", userVO=" + userVO +
                ", categoryVO=" + categoryVO +
                ", fileVO=" + fileVO +
                ", replyVO=" + replies +
                ", viewCount=" + viewCount +
                ", favoriteCount=" + favoriteCount +
                '}';
    }

    public static class Builder {
        private NoteVO noteVO;
        private UserVO userVO;
        private CategoryVO categoryVO;
        private FileVO fileVO;
        private List<ReplyVO> replies;
        private int viewCount;
        private int favoriteCount;
        

        public Builder noteVO(NoteVO noteVO) {
            this.noteVO = noteVO;
            return this;
        }

        public Builder userVO(UserVO userVO) {
            this.userVO = userVO;
            return this;
        }

        public Builder categoryVO(CategoryVO categoryVO) {
            this.categoryVO = categoryVO;
            return this;
        }

        public Builder fileVO(FileVO fileVO) {
            this.fileVO = fileVO;
            return this;
        }
        
        public Builder replies(List<ReplyVO> replies) {
            this.replies = replies;
            return this;
        }
        
        public Builder viewCount(int viewCount) {
            this.viewCount = viewCount;
            return this;
        }
        
        public Builder favoriteCount(int favoriteCount) {
            this.favoriteCount = favoriteCount;
            return this;
        }

        public QuestionVO build() {
            return new QuestionVO(this);
        }
    }
}
