package com.ksw.vo;

public class NoteUserVO {

    private Integer userNo;
    private Integer noteNo;

    // 기본 생성자
    public NoteUserVO() {}

    // Getter 및 Setter
    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public Integer getNoteNo() {
        return noteNo;
    }

    public void setNoteNo(Integer noteNo) {
        this.noteNo = noteNo;
    }

    // 빌더 패턴 구현
    public static class Builder {
        private Integer userNo;
        private Integer noteNo;

        public Builder userNo(Integer userNo) {
            this.userNo = userNo;
            return this;
        }

        public Builder noteNo(Integer noteNo) {
            this.noteNo = noteNo;
            return this;
        }

        public NoteUserVO build() {
            NoteUserVO noteUserVO = new NoteUserVO();
            noteUserVO.userNo = this.userNo;
            noteUserVO.noteNo = this.noteNo;
            return noteUserVO;
        }
    }
}
