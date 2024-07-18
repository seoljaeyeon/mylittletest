package com.ksw.dto.forObject.relation;

import java.io.Serializable;

import com.ksw.dto.forObject.entity.FileDTO;
import com.ksw.dto.forObject.entity.NoteDTO;

public class FileNoteDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	

    private NoteDTO noteDTO;
    private FileDTO fileDTO;

    // 기본 생성자
    public FileNoteDTO() {}
    
    // 기본 생성자
    public FileNoteDTO(NoteDTO noteDTO, FileDTO fileDTO) {
    	super();
    	this.noteDTO = noteDTO;
    	this.fileDTO = fileDTO;
    }

	public NoteDTO getNoteDTO() {
		return noteDTO;
	}

	public void setNoteDTO(NoteDTO noteDTO) {
		this.noteDTO = noteDTO;
	}

	public FileDTO getFileDTO() {
		return fileDTO;
	}

	public void setFileDTO(FileDTO fileDTO) {
		this.fileDTO = fileDTO;
	}
}
