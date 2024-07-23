package com.ksw.vo.function;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.ksw.vo.forObject.entity.CategoryVO;
import com.ksw.vo.forObject.entity.FileVO;
import com.ksw.vo.forObject.entity.NoteVO;
import com.ksw.vo.forObject.entity.UserVO;

public final class QuestionVO implements Serializable {

	private static final long serialVersionUID = 1L;
    private final NoteVO noteVO;
    private final UserVO writerVO;
    private final CategoryVO categoryVO;
	private final List<FileVO> filelist;
    private final List<Map<String, Object>> replies;
    private final Integer todayNoteViewInCategory;
    private final Integer viewCount;
    private final Integer favoriteCount;
    private final Integer answerType;
    private final Boolean isFavorite;

    private QuestionVO(Builder builder) {
        this.noteVO = builder.noteVO;
		this.writerVO = builder.writerVO;
        this.categoryVO = builder.categoryVO;
        this.filelist = builder.filelist;
        this.replies = builder.replies;
        this.viewCount = builder.viewCount;
        this.favoriteCount = builder.favoriteCount;
        this.answerType = builder.answerType;
        this.isFavorite = builder.isFavorite;
        this.todayNoteViewInCategory = builder.todayNoteViewInCategory;
    }

    public Integer getTodayNoteViewInCategory() {
    	return todayNoteViewInCategory;
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

    public CategoryVO getCategoryVO() {
        return categoryVO;
    }

    public List<FileVO> getFileVO() {
        return filelist;
    }

    public List<Map<String, Object>> getReplies() {
		return replies;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public Integer getFavoriteCount() {
		return favoriteCount;
	}
	
	public Integer getAnswerType() {
		return answerType;
	}



    public static class Builder {
        private UserVO writerVO;
		private NoteVO noteVO;
        private CategoryVO categoryVO;
        private List<FileVO> filelist;
        private List<Map<String, Object>> replies;
        private Integer viewCount;
        private Integer favoriteCount;
        private Integer answerType;
        private Boolean isFavorite;
        private Integer todayNoteViewInCategory;
        
        public Builder writerVO(UserVO writerVO) {
        	this.writerVO = writerVO;
			return this;
        }

        public Builder noteVO(NoteVO noteVO) {
            this.noteVO = noteVO;
            return this;
        }

        public Builder categoryVO(CategoryVO categoryVO) {
            this.categoryVO = categoryVO;
            return this;
        }

        public Builder filelist(List<FileVO> filelist) {
            this.filelist = filelist;
            return this;
        }
        
        public Builder replies(List<Map<String, Object>> replies) {
            this.replies = replies;
            return this;
        }
        
        public Builder viewCount(Integer viewCount) {
            this.viewCount = viewCount;
            return this;
        }
        
        public Builder favoriteCount(Integer favoriteCount) {
            this.favoriteCount = favoriteCount;
            return this;
        }
        
        public Builder answerType(Integer answerType) {
        	this.answerType = answerType;
        	return this;
        }
        
        public Builder isFavorite(Boolean isFavorite) {
        	this.isFavorite = isFavorite;
        	return this;
        }
        
        @Override
		public int hashCode() {
			return Objects.hash(answerType, categoryVO, favoriteCount, filelist, isFavorite, noteVO, todayNoteViewInCategory,
					replies, viewCount, writerVO);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Builder other = (Builder) obj;
			return Objects.equals(answerType, other.answerType) && Objects.equals(categoryVO, other.categoryVO)
					&& Objects.equals(favoriteCount, other.favoriteCount) && Objects.equals(filelist, other.filelist)
					&& Objects.equals(isFavorite, other.isFavorite) && Objects.equals(noteVO, other.noteVO)
					&& Objects.equals(todayNoteViewInCategory, other.todayNoteViewInCategory) && Objects.equals(replies, other.replies)
					&& Objects.equals(viewCount, other.viewCount) && Objects.equals(writerVO, other.writerVO);
		}

		@Override
		public String toString() {
			return "Builder [writerVO=" + writerVO + ", noteVO=" + noteVO + ", categoryVO=" + categoryVO + ", fileVO="
					+ filelist + ", replies=" + replies + ", viewCount=" + viewCount + ", favoriteCount=" + favoriteCount
					+ ", answerType=" + answerType + ", isFavorite=" + isFavorite + ", todayNoteViewInCategory=" + todayNoteViewInCategory
					+ "]";
		}

		public Builder todayNoteViewInCategory(Integer todayNoteViewInCategory) {
        	this.todayNoteViewInCategory = todayNoteViewInCategory;
        	return this;
        }

        public QuestionVO build() {
            return new QuestionVO(this);
        }
    }
}
