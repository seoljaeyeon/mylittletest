package com.ksw.dto;

public class ViewUserNoteDTO {

    private Integer userNo;
    private Integer noteNo;
    private Integer postViewNo;

    // 기본 생성자
    public ViewUserNoteDTO() {}

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

    public Integer getPostViewNo() {
        return postViewNo;
    }

    public void setPostViewNo(Integer postViewNo) {
        this.postViewNo = postViewNo;
    }

    // 빌더 패턴 구현
    public static class Builder {
        private Integer userNo;
        private Integer noteNo;
        private Integer postViewNo;

        public Builder userNo(Integer userNo) {
            this.userNo = userNo;
            return this;
        }

        public Builder noteNo(Integer noteNo) {
            this.noteNo = noteNo;
            return this;
        }

        public Builder postViewNo(Integer postViewNo) {
            this.postViewNo = postViewNo;
            return this;
        }

        public ViewUserNoteDTO build() {
            ViewUserNoteDTO viewUserNoteDTO = new ViewUserNoteDTO();
            viewUserNoteDTO.userNo = this.userNo;
            viewUserNoteDTO.noteNo = this.noteNo;
            viewUserNoteDTO.postViewNo = this.postViewNo;
            return viewUserNoteDTO;
        }
    }
}
