package com.ksw.vo.forObject.relation;

import java.io.Serializable;
import java.util.Objects;

import com.ksw.vo.forObject.entity.AnnouncementVO;
import com.ksw.vo.forObject.entity.FileVO;

public final class FileAnnouncementVO implements Serializable {

	private static final long serialVersionUID = 1L;
    private final AnnouncementVO announcementVO;
    private final FileVO fileVO;

    private FileAnnouncementVO(Builder builder) {
        this.announcementVO = builder.announcementVO;
        this.fileVO = builder.fileVO;
    }

    public AnnouncementVO getAnnouncementVO() {
		return announcementVO;
	}

	public FileVO getFileVO() {
		return fileVO;
	}

	public FileAnnouncementVO(AnnouncementVO announcementVO, FileVO fileVO) {
		super();
		this.announcementVO = announcementVO;
		this.fileVO = fileVO;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileAnnouncementVO that = (FileAnnouncementVO) o;
        return Objects.equals(announcementVO, that.announcementVO) &&
                Objects.equals(fileVO, that.fileVO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(announcementVO, fileVO);
    }



	@Override
    public String toString() {
        return "FileAnnouncementVO{" +
                "announcementVO=" + announcementVO +
                ", fileVO=" + fileVO +
                '}';
    }

    public static class Builder {
        private AnnouncementVO announcementVO;
        private FileVO fileVO;

        public Builder announcementVO(AnnouncementVO announcementVO) {
            this.announcementVO = announcementVO;
            return this;
        }

        public Builder fileVO(FileVO fileVO) {
            this.fileVO = fileVO;
            return this;
        }

        public FileAnnouncementVO build() {
            return new FileAnnouncementVO(this);
        }
    }
}
