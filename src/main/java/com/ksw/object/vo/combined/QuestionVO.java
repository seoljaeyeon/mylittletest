package com.ksw.object.vo.combined;

import java.util.Objects;

import com.ksw.object.vo.object.CategoryVO;
import com.ksw.object.vo.object.FileVO;
import com.ksw.object.vo.object.NoteVO;
import com.ksw.object.vo.object.UserVO;

public final class QuestionVO {
    private final NoteVO noteVO;
    private final UserVO userVO;
    private final CategoryVO categoryVO;
    private final FileVO fileVO;

    private QuestionVO(Builder builder) {
        this.noteVO = builder.noteVO;
        this.userVO = builder.userVO;
        this.categoryVO = builder.categoryVO;
        this.fileVO = builder.fileVO;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionVO that = (QuestionVO) o;
        return Objects.equals(noteVO, that.noteVO) &&
                Objects.equals(userVO, that.userVO) &&
                Objects.equals(categoryVO, that.categoryVO) &&
                Objects.equals(fileVO, that.fileVO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteVO, userVO, categoryVO, fileVO);
    }

    @Override
    public String toString() {
        return "WriteVO{" +
                "noteVO=" + noteVO +
                ", userVO=" + userVO +
                ", categoryVO=" + categoryVO +
                ", fileVO=" + fileVO +
                '}';
    }

    public static class Builder {
        private NoteVO noteVO;
        private UserVO userVO;
        private CategoryVO categoryVO;
        private FileVO fileVO;

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

        public QuestionVO build() {
            return new QuestionVO(this);
        }
    }
}
