package com.ksw.vo;

public class NoteReplyVO {

    private Integer noteNo;
    private Integer replyNo;

    // 기본 생성자
    public NoteReplyVO() {}

    // Getter 및 Setter
    public Integer getNoteNo() {
        return noteNo;
    }

    public void setNoteNo(Integer noteNo) {
        this.noteNo = noteNo;
    }

    public Integer getReplyNo() {
        return replyNo;
    }

    public void setReplyNo(Integer replyNo) {
        this.replyNo = replyNo;
    }

    // 빌더 패턴 구현
    public static class Builder {
        private Integer noteNo;
        private Integer replyNo;

        public Builder noteNo(Integer noteNo) {
            this.noteNo = noteNo;
            return this;
        }

        public Builder replyNo(Integer replyNo) {
            this.replyNo = replyNo;
            return this;
        }

        public NoteReplyVO build() {
            NoteReplyVO noteReplyVO = new NoteReplyVO();
            noteReplyVO.noteNo = this.noteNo;
            noteReplyVO.replyNo = this.replyNo;
            return noteReplyVO;
        }
    }
}
