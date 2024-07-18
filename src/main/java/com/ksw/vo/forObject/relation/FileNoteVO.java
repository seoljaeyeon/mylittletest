package com.ksw.vo.forObject.relation;

import java.io.Serializable;
import java.util.Objects;

import com.ksw.vo.forObject.entity.FileVO;
import com.ksw.vo.forObject.entity.NoteVO;

public final class FileNoteVO implements Serializable {

	private static final long serialVersionUID = 1L;
    private final NoteVO noteVO;
    private final FileVO fileVO;

    private FileNoteVO(Builder builder) {
        this.noteVO = builder.noteVO;
        this.fileVO = builder.fileVO;
    }


    public FileNoteVO(NoteVO noteVO, FileVO fileVO) {
		super();
		this.noteVO = noteVO;
		this.fileVO = fileVO;
	}


	public NoteVO getNoteVO() {
		return noteVO;
	}


	public FileVO getFileVO() {
		return fileVO;
	}


	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileNoteVO that = (FileNoteVO) o;
        return Objects.equals(noteVO, that.noteVO) &&
                Objects.equals(fileVO, that.fileVO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteVO, fileVO);
    }

    @Override
    public String toString() {
        return "FileNoteVO{" +
                "noteVO=" + noteVO +
                ", fileVO=" + fileVO +
                '}';
    }

    public static class Builder {
        private NoteVO noteVO;
        private FileVO fileVO;

        public Builder noteVO(NoteVO noteVO) {
            this.noteVO = noteVO;
            return this;
        }

        public Builder fileVO(FileVO fileVO) {
            this.fileVO = fileVO;
            return this;
        }

        public FileNoteVO build() {
            return new FileNoteVO(this);
        }
    }
}
