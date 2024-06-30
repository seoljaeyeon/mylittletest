package com.ksw.vo;

public class FileNoteVO {

    private Integer noteNo;
    private Integer fileNo;

    // 기본 생성자
    public FileNoteVO() {}

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

    // 빌더 패턴 구현
    public static class Builder {
        private Integer noteNo;
        private Integer fileNo;

        public Builder noteNo(Integer noteNo) {
            this.noteNo = noteNo;
            return this;
        }

        public Builder fileNo(Integer fileNo) {
            this.fileNo = fileNo;
            return this;
        }

        public FileNoteVO build() {
            FileNoteVO fileNoteVO = new FileNoteVO();
            fileNoteVO.noteNo = this.noteNo;
            fileNoteVO.fileNo = this.fileNo;
            return fileNoteVO;
        }
    }
}
