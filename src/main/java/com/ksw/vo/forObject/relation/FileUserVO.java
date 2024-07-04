package com.ksw.vo.forObject.relation;

import java.util.Objects;

public final class FileUserVO {
    private final Integer userNo;
    private final Integer fileNo;

    private FileUserVO(Builder builder) {
        this.userNo = builder.userNo;
        this.fileNo = builder.fileNo;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public Integer getFileNo() {
        return fileNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileUserVO that = (FileUserVO) o;
        return Objects.equals(userNo, that.userNo) &&
                Objects.equals(fileNo, that.fileNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userNo, fileNo);
    }

    @Override
    public String toString() {
        return "FileUserVO{" +
                "userNo=" + userNo +
                ", fileNo=" + fileNo +
                '}';
    }

    public static class Builder {
        private Integer userNo;
        private Integer fileNo;

        public Builder userNo(Integer userNo) {
            this.userNo = userNo;
            return this;
        }

        public Builder fileNo(Integer fileNo) {
            this.fileNo = fileNo;
            return this;
        }

        public FileUserVO build() {
            return new FileUserVO(this);
        }
    }
}
