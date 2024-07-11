package com.ksw.dto.forObject.relation;

import com.ksw.dto.forObject.entity.NoteDTO;
import com.ksw.dto.forObject.entity.ViewDTO;

public class NoteViewDTO {
    
    private ViewDTO viewDTO;
    private NoteDTO noteDTO;
    
    // 기본생성자
    public NoteViewDTO() {
    	
    }
    
	public ViewDTO getViewDTO() {
		return viewDTO;
	}
	public void setViewDTO(ViewDTO viewDTO) {
		this.viewDTO = viewDTO;
	}
	public NoteDTO getNoteDTO() {
		return noteDTO;
	}
	public void setNoteDTO(NoteDTO noteDTO) {
		this.noteDTO = noteDTO;
	}


}
