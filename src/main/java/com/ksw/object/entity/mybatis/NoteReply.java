package com.ksw.object.entity.mybatis;

public class NoteReply {

    private Integer noteNo;
    private Integer replyNo;

    // 기본 생성자
    public NoteReply() {}

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
}
