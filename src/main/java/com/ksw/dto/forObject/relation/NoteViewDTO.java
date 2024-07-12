package com.ksw.dto.forObject.relation;

import java.io.Serializable;

import com.ksw.dto.forObject.entity.NoteDTO;
import com.ksw.dto.forObject.entity.UserDTO;
import com.ksw.dto.forObject.entity.ViewDTO;

public class NoteViewDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
    
    private ViewDTO viewDTO;
	private NoteDTO noteDTO;
    private UserDTO userDTO;
    
    // 기본생성자
    public NoteViewDTO() {
    }
    
    public NoteViewDTO(NoteDTO noteDTO, ViewDTO viewDTO, UserDTO userDTO) {
    	this.noteDTO = noteDTO;
    	this.viewDTO = viewDTO;
    	this.userDTO = userDTO;
    }
    
    public UserDTO getUserDTO() {
    	return userDTO;
    }
    
    public void setUserDTO(UserDTO userDTO) {
    	this.userDTO = userDTO;
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
