package com.ksw.dto.forObject.relation;

import java.io.Serializable;

import com.ksw.dto.forObject.entity.FavoriteDTO;
import com.ksw.dto.forObject.entity.NoteDTO;
import com.ksw.dto.forObject.entity.UserDTO;

public class FavoriteNoteDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	

    private UserDTO userDTO;
    private NoteDTO noteDTO;
    private FavoriteDTO favoriteDTO;

    // 기본 생성자
    public FavoriteNoteDTO() {}

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

	public FavoriteDTO getFavoriteDTO() {
		return favoriteDTO;
	}

	public void setFavoriteDTO(FavoriteDTO favoriteDTO) {
		this.favoriteDTO = favoriteDTO;
	}
}
