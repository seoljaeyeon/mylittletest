package com.ksw.vo.forObject.relation;

import java.io.Serializable;
import java.util.Objects;

import com.ksw.vo.forObject.entity.CategoryVO;
import com.ksw.vo.forObject.entity.NoteVO;

public final class NoteCategoryVO implements Serializable {

	private static final long serialVersionUID = 1L;
    private final CategoryVO categoryVO;
    private final NoteVO noteVO;

    private NoteCategoryVO(Builder builder) {
        this.categoryVO = builder.categoryVO;
        this.noteVO = builder.noteVO;
    }


    public NoteCategoryVO(CategoryVO categoryVO, NoteVO noteVO) {
		super();
		this.categoryVO = categoryVO;
		this.noteVO = noteVO;
	}


	public CategoryVO getCategoryVO() {
		return categoryVO;
	}


	public NoteVO getNoteVO() {
		return noteVO;
	}


	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteCategoryVO that = (NoteCategoryVO) o;
        return Objects.equals(categoryVO, that.categoryVO) &&
                Objects.equals(noteVO, that.noteVO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryVO, noteVO);
    }

    @Override
    public String toString() {
        return "NoteCategoryVO{" +
                "categoryVO=" + categoryVO +
                ", noteVO=" + noteVO +
                '}';
    }

    public static class Builder {
        private CategoryVO categoryVO;
        private NoteVO noteVO;

        public Builder categoryVO(CategoryVO categoryVO) {
            this.categoryVO = categoryVO;
            return this;
        }

        public Builder noteVO(NoteVO noteVO) {
            this.noteVO = noteVO;
            return this;
        }

        public NoteCategoryVO build() {
            return new NoteCategoryVO(this);
        }
    }
}
