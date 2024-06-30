package com.ksw.dto;

public class ReplyUserDTO {

    private Integer userNo;
    private Integer replyNo;

    // 기본 생성자
    public ReplyUserDTO() {}

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

    // 빌더 패턴 구현
    public static class Builder {
        private Integer userNo;
        private Integer replyNo;

        public Builder userNo(Integer userNo) {
            this.userNo = userNo;
            return this;
        }

        public Builder replyNo(Integer replyNo) {
            this.replyNo = replyNo;
            return this;
        }

        public ReplyUserDTO build() {
            ReplyUserDTO replyUserDTO = new ReplyUserDTO();
            replyUserDTO.userNo = this.userNo;
            replyUserDTO.replyNo = this.replyNo;
            return replyUserDTO;
        }
    }
}
