package com.ksw.object.entity.mybatis;

public class FileUser {

    private Integer userNo;
    private Integer fileNo;

    // 기본 생성자
    public FileUser() {}

    // Getter 및 Setter
    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public Integer getFileNo() {
        return fileNo;
    }

    public void setFileNo(Integer fileNo) {
        this.fileNo = fileNo;
    }
}
