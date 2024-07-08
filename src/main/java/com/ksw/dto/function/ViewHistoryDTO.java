package com.ksw.dto.function;

import java.sql.Timestamp;

public class ViewHistoryDTO {

    private Integer noteNo;
    private Integer noteViewNo;
    private Timestamp createAt;
    
	public Integer getNoteNo() {
		return noteNo;
	}
	public void setNoteNo(Integer noteNo) {
		this.noteNo = noteNo;
	}
	public Integer getNoteViewNo() {
		return noteViewNo;
	}
	public void setNoteViewNo(Integer noteViewNo) {
		this.noteViewNo = noteViewNo;
	}
	public Timestamp getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Timestamp createdAt) {
		this.createAt = createdAt;
	}
    
    
}
