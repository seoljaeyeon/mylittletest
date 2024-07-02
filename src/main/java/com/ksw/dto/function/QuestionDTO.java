package com.ksw.dto.function;

import java.sql.Timestamp;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ksw.dto.forObject.object.CategoryDTO;
import com.ksw.dto.forObject.object.FileDTO;
import com.ksw.dto.forObject.object.NoteDTO;
import com.ksw.dto.forObject.object.UserDTO;

public class QuestionDTO {

	private UserDTO userDTO;
	private NoteDTO noteDTO;
	private CategoryDTO categoryDTO;
	private FileDTO fileDTO;
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
	public CategoryDTO getCategoryDTO() {
		return categoryDTO;
	}
	public void setCategoryDTO(CategoryDTO categoryDTO) {
		this.categoryDTO = categoryDTO;
	}
	public FileDTO getFileDTO() {
		return fileDTO;
	}
	public void setFileDTO(FileDTO fileDTO) {
		this.fileDTO = fileDTO;
	}
}
