package com.ksw.dto;

public class NoteReplyDTO {

    private Integer noteNo;
    private Integer replyNo;

    // 기본 생성자
    public NoteReplyDTO() {}

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

        public NoteReplyDTO build() {
            NoteReplyDTO noteReplyDTO = new NoteReplyDTO();
            noteReplyDTO.noteNo = this.noteNo;
            noteReplyDTO.replyNo = this.replyNo;
            return noteReplyDTO;
        }
    }
}
