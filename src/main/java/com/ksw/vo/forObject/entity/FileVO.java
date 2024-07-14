package com.ksw.vo.forObject.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public final class FileVO implements Serializable {

	private static final long serialVersionUID = 1L;
    private final Integer fileNo;
    private final String savedName;
    private final String uploadName;
    private final Timestamp createdAt;

    private FileVO(Builder builder) {
        this.fileNo = builder.fileNo;
        this.savedName = builder.savedName;
        this.uploadName = builder.uploadName;
        this.createdAt = builder.createdAt;
    }

    public Integer getFileNo() {
        return fileNo;
    }

    public String getSavedName() {
        return savedName;
    }

    public String getUploadName() {
        return uploadName;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileVO fileVO = (FileVO) o;
        return Objects.equals(fileNo, fileVO.fileNo) &&
                Objects.equals(savedName, fileVO.savedName) &&
                Objects.equals(uploadName, fileVO.uploadName) &&
                Objects.equals(createdAt, fileVO.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileNo, savedName, uploadName, createdAt);
    }

    @Override
    public String toString() {
        return "FileVO{" +
                "fileNo=" + fileNo +
                ", savedName='" + savedName + '\'' +
                ", uploadName='" + uploadName + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    public static class Builder {
        private Integer fileNo;
        private String savedName;
        private String uploadName;
        private Timestamp createdAt;

        public Builder fileNo(Integer fileNo) {
            this.fileNo = fileNo;
            return this;
        }

        public Builder savedName(String savedName) {
            this.savedName = savedName;
            return this;
        }

        public Builder uploadName(String uploadName) {
            this.uploadName = uploadName;
            return this;
        }

        public Builder createdAt(Timestamp createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public FileVO build() {
            return new FileVO(this);
        }
    }
}
