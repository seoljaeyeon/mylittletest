package com.ksw.dto.forObject.relation;

import java.sql.Timestamp;

import com.ksw.dto.forObject.entity.AnswerDTO;
import com.ksw.dto.forObject.entity.NoteDTO;
import com.ksw.dto.forObject.entity.UserDTO;

public class AnswerHistoryDTO {
    private NoteDTO noteDTO;
    private AnswerDTO answerDTO;
    private UserDTO userDTO;
    private Timestamp createdAt;
    private Timestamp updatedAt;
	public NoteDTO getNoteDTO() {
		return noteDTO;
	}
	public void setNoteDTO(NoteDTO noteDTO) {
		this.noteDTO = noteDTO;
	}
	public AnswerDTO getAnswerDTO() {
		return answerDTO;
	}
	public void setAnswerDTO(AnswerDTO answerDTO) {
		this.answerDTO = answerDTO;
	}
	public UserDTO getUserDTO() {
		return userDTO;
	}
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
}