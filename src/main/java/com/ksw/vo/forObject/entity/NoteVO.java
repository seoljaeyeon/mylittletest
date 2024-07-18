package com.ksw.vo.forObject.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public final class NoteVO implements Serializable {

	private static final long serialVersionUID = 1L;
    private final Integer noteNo;
    private final String noteTitle;
    private final String noteContent;
    private final String noteCommentary;
    private final String noteHint;
    private final String noteAnswer;
    private final Boolean isActive;
    private final Timestamp createdAt;
    private final Timestamp updatedAt;

    private NoteVO(Builder builder) {
        this.noteNo = builder.noteNo;
        this.noteTitle = builder.noteTitle;
        this.noteContent = builder.noteContent;
        this.noteCommentary = builder.noteCommentary;
        this.noteHint = builder.noteHint;
        this.noteAnswer = builder.noteAnswer;
        this.isActive = builder.isActive;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public Integer getNoteNo() {
        return noteNo;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }
    
    public String getNoteCommentary() {
		return noteCommentary;
	}

    public String getNoteHint() {
        return noteHint;
    }

    public String getNoteAnswer() {
        return noteAnswer;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteVO noteVO = (NoteVO) o;
        return Objects.equals(noteNo, noteVO.noteNo) &&
                Objects.equals(noteTitle, noteVO.noteTitle) &&
                Objects.equals(noteContent, noteVO.noteContent) &&
                Objects.equals(noteCommentary, noteVO.noteCommentary) &&
                Objects.equals(noteHint, noteVO.noteHint) &&
                Objects.equals(noteAnswer, noteVO.noteAnswer) &&
                Objects.equals(isActive, noteVO.isActive) &&
                Objects.equals(createdAt, noteVO.createdAt) &&
                Objects.equals(updatedAt, noteVO.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteNo, noteTitle, noteContent, noteCommentary, noteHint, noteAnswer, isActive, createdAt, updatedAt);
    }

	@Override
    public String toString() {
        return "NoteVO{" +
                "noteNo=" + noteNo +
                ", noteTitle='" + noteTitle + '\'' +
                ", noteContent='" + noteContent + '\'' +
                ", noteCommentary='" + noteCommentary + '\'' +
                ", noteHint='" + noteHint + '\'' +
                ", noteAnswer='" + noteAnswer + '\'' +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public static class Builder {
        private Integer noteNo;
        private String noteTitle;
        private String noteContent;
        private String noteCommentary;
        private String noteHint;
        private String noteAnswer;
        private Boolean isActive;
        private Timestamp createdAt;
        private Timestamp updatedAt;

        public Builder noteNo(Integer noteNo) {
            this.noteNo = noteNo;
            return this;
        }

        public Builder noteTitle(String noteTitle) {
            this.noteTitle = noteTitle;
            return this;
        }

        public Builder noteContent(String noteContent) {
            this.noteContent = noteContent;
            return this;
        }
        
        public Builder noteCommentary(String noteCommentary) {
            this.noteCommentary = noteCommentary;
            return this;
        }

        public Builder noteHint(String noteHint) {
            this.noteHint = noteHint;
            return this;
        }

        public Builder noteAnswer(String noteAnswer) {
            this.noteAnswer = noteAnswer;
            return this;
        }

        public Builder isActive(Boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public Builder createdAt(Timestamp createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(Timestamp updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public NoteVO build() {
            return new NoteVO(this);
        }
    }
}
