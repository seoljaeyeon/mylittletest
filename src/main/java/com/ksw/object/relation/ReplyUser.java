package com.ksw.object.relation;

public class ReplyUser {

    private Integer userNo;
    private Integer replyNo;

    // 기본 생성자
    public ReplyUser() {}

    // Getter 및 Setter
    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public Integer getReplyNo() {
        return replyNo;
    }

    public void setReplyNo(Integer replyNo) {
        this.replyNo = replyNo;
    }
}
