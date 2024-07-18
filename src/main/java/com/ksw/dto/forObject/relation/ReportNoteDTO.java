package com.ksw.dto.forObject.relation;

import java.io.Serializable;

import com.ksw.dto.forObject.entity.NoteDTO;
import com.ksw.dto.forObject.entity.ReportDTO;
import com.ksw.dto.forObject.entity.UserDTO;

public class ReportNoteDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	

    private UserDTO userDTO;
    private NoteDTO noteDTO;
    private ReportDTO reportDTO;

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

	public ReportDTO getReportDTO() {
		return reportDTO;
	}

	public void setReportDTO(ReportDTO reportDTO) {
		this.reportDTO = reportDTO;
	}

	// 기본 생성자
    public ReportNoteDTO() {}
}
