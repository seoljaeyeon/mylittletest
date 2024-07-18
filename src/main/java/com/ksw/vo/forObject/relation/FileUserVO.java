package com.ksw.vo.forObject.relation;

import java.io.Serializable;
import java.util.Objects;

import com.ksw.vo.forObject.entity.FileVO;
import com.ksw.vo.forObject.entity.UserVO;

public final class FileUserVO implements Serializable {

	private static final long serialVersionUID = 1L;
    private final UserVO userVO;
    private final FileVO fileVO;

    private FileUserVO(Builder builder) {
        this.userVO = builder.userVO;
        this.fileVO = builder.fileVO;
    }


    public FileUserVO(UserVO userVO, FileVO fileVO) {
		super();
		this.userVO = userVO;
		this.fileVO = fileVO;
	}


	public UserVO getUserVO() {
		return userVO;
	}


	public FileVO getFileVO() {
		return fileVO;
	}


	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileUserVO that = (FileUserVO) o;
        return Objects.equals(userVO, that.userVO) &&
                Objects.equals(fileVO, that.fileVO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userVO, fileVO);
    }

    @Override
    public String toString() {
        return "FileUserVO{" +
                "userVO=" + userVO +
                ", fileVO=" + fileVO +
                '}';
    }

    public static class Builder {
        private UserVO userVO;
        private FileVO fileVO;

        public Builder userVO(UserVO userVO) {
            this.userVO = userVO;
            return this;
        }

        public Builder fileVO(FileVO fileVO) {
            this.fileVO = fileVO;
            return this;
        }

        public FileUserVO build() {
            return new FileUserVO(this);
        }
    }
}
