package com.ksw.dto.forObject.relation;

import com.ksw.dto.forObject.entity.NoteDTO;
import com.ksw.dto.forObject.entity.UserDTO;

public class NoteUserDTO {

    private UserDTO userDTO;
    private NoteDTO noteDTO;

    // 기본 생성자
    public NoteUserDTO() {}

    public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public NoteDTO getNoteDTO() {
		return noteDTO;
	}

	public void setNoteDTO(NoteDTO noteDTO) {
		this.noteDTO = noteDTO;
	}
}
