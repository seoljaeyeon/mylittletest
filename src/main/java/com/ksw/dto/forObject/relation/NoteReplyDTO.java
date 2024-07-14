package com.ksw.dto.forObject.relation;

import java.io.Serializable;

import com.ksw.dto.forObject.entity.NoteDTO;
import com.ksw.dto.forObject.entity.ReplyDTO;

public class NoteReplyDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	

    private NoteDTO noteDTO;
    private ReplyDTO replyDTO;

    // 기본 생성자
    public NoteReplyDTO() {}

	public NoteDTO getNoteDTO() {
		return noteDTO;
	}

	public void setNoteDTO(NoteDTO noteDTO) {
		this.noteDTO = noteDTO;
	}

	public ReplyDTO getReplyDTO() {
		return replyDTO;
	}

	public void setReplyDTO(ReplyDTO replyDTO) {
		this.replyDTO = replyDTO;
	}

}
