package com.ksw.object.entity.mybatis;

public class FileNote {

    private Integer noteNo;
    private Integer fileNo;

    // 기본 생성자
    public FileNote() {}

    // Getter 및 Setter
    public Integer getNoteNo() {
        return noteNo;
    }

    public void setNoteNo(Integer noteNo) {
        this.noteNo = noteNo;
    }

    public Integer getFileNo() {
        return fileNo;
    }

    public void setFileNo(Integer fileNo) {
        this.fileNo = fileNo;
    }
}
