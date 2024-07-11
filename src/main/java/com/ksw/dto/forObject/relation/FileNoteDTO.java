package com.ksw.dto.forObject.relation;

import com.ksw.dto.forObject.entity.FileDTO;
import com.ksw.dto.forObject.entity.NoteDTO;

public class FileNoteDTO {

    private NoteDTO noteDTO;
    private FileDTO fileDTO;

    // 기본 생성자
    public FileNoteDTO() {}

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
