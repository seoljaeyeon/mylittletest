package com.ksw.dto;

public class NoteCategoryDTO {

    private Integer categoryNo;
    private Integer noteNo;

    // 기본 생성자
    public NoteCategoryDTO() {}

    // Getter 및 Setter
    public Integer getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(Integer categoryNo) {
        this.categoryNo = categoryNo;
    }

    public Integer getNoteNo() {
        return noteNo;
    }

    public void setNoteNo(Integer noteNo) {
        this.noteNo = noteNo;
    }

    // 빌더 패턴 구현
    public static class Builder {
        private Integer categoryNo;
        private Integer noteNo;

        public Builder categoryNo(Integer categoryNo) {
            this.categoryNo = categoryNo;
            return this;
        }

        public Builder noteNo(Integer noteNo) {
            this.noteNo = noteNo;
            return this;
        }

        public NoteCategoryDTO build() {
            NoteCategoryDTO noteCategoryDTO = new NoteCategoryDTO();
            noteCategoryDTO.categoryNo = this.categoryNo;
            noteCategoryDTO.noteNo = this.noteNo;
            return noteCategoryDTO;
        }
    }
}
