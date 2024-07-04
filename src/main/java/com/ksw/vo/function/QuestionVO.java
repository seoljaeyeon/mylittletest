package com.ksw.vo.function;

import java.util.List;
import java.util.Objects;

import com.ksw.vo.forObject.entity.CategoryVO;
import com.ksw.vo.forObject.entity.FileVO;
import com.ksw.vo.forObject.entity.NoteVO;
import com.ksw.vo.forObject.entity.ReplyVO;
import com.ksw.vo.forObject.entity.UserVO;

public final class QuestionVO {
    private final NoteVO noteVO;
    private final UserVO userVO;
    private final UserVO writerVO;
    private final CategoryVO categoryVO;
    private final FileVO fileVO;
    private final List<ReplyVO> replies;
    private final int viewCount;
    private final int favoriteCount;
    private final int answerType;
    private final Boolean isFavorite;

    private QuestionVO(Builder builder) {
        this.noteVO = builder.noteVO;
        this.userVO = builder.userVO;
		this.writerVO = builder.writerVO;
        this.categoryVO = builder.categoryVO;
        this.fileVO = builder.fileVO;
        this.replies = builder.replies;
        this.viewCount = builder.viewCount;
        this.favoriteCount = builder.favoriteCount;
        this.answerType = builder.answerType;
        this.isFavorite = builder.isFavorite;
    }

    public NoteVO getNoteVO() {
        return noteVO;
    }

    public UserVO getWriterVO() {
		return writerVO;
	}

	public Boolean getIsFavorite() {
		return isFavorite;
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
	
	public int getAnswerType() {
		return answerType;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuestionVO other = (QuestionVO) obj;
		return answerType == other.answerType && Objects.equals(categoryVO, other.categoryVO)
				&& favoriteCount == other.favoriteCount && Objects.equals(fileVO, other.fileVO)
				&& Objects.equals(isFavorite, other.isFavorite) && Objects.equals(noteVO, other.noteVO)
				&& Objects.equals(replies, other.replies) && Objects.equals(userVO, other.userVO)
				&& viewCount == other.viewCount && Objects.equals(writerVO, other.writerVO);
	}

    @Override
	public int hashCode() {
		return Objects.hash(answerType, categoryVO, favoriteCount, fileVO, isFavorite, noteVO, replies, userVO,
				viewCount, writerVO);
	}

    @Override
	public String toString() {
		return "QuestionVO [noteVO=" + noteVO + 
				", userVO=" + userVO + 
				", writerVO=" + writerVO + 
				", categoryVO="	+ categoryVO + 
				", fileVO=" + fileVO + 
				", replies=" + replies + 
				", viewCount=" + viewCount 
				+ ", favoriteCount=" + favoriteCount + 
				", answerType=" + answerType + 
				", isFavorite=" + isFavorite
				+ "]";
	}

    public static class Builder {
        private UserVO writerVO;
		private NoteVO noteVO;
        private UserVO userVO;
        private CategoryVO categoryVO;
        private FileVO fileVO;
        private List<ReplyVO> replies;
        private int viewCount;
        private int favoriteCount;
        private int answerType;
        private Boolean isFavorite;
        
        public Builder writerVO(UserVO writerVO) {
        	this.writerVO = writerVO;
			return this;
        }

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
        
        public Builder answerType(int answerType) {
        	this.answerType = answerType;
        	return this;
        }
        
        public Builder isFavorite(Boolean isFavorite) {
        	this.isFavorite = isFavorite;
        	return this;
        }

        public QuestionVO build() {
            return new QuestionVO(this);
        }
    }
}
