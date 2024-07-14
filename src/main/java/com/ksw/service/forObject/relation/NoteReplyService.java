package com.ksw.service.forObject.relation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dto.forObject.relation.NoteReplyDTO;
import com.ksw.object.relation.NoteReply;
import com.ksw.service.forObject.entity.ReplyService;
import com.ksw.service.forObject.entity.NoteService;
import com.ksw.vo.forObject.relation.NoteReplyVO;

@Service
public class NoteReplyService {

	@Autowired
	private NoteService noteService;
	@Autowired
	private ReplyService replyService;
	
    // Entity -> DTO 변환 메소드
    public NoteReplyDTO convertToDTO(NoteReply noteReplyEntity) {
    	NoteReplyDTO dto = new NoteReplyDTO();
    	if (noteReplyEntity == null) {
    		System.out.println("NoteReply to NoteReplyDTO failed. Empty NoteReplyDTO created. NoteReply is null");   	
    		return dto;
    	}
    	dto.setNoteDTO(noteService.convertToDTO(noteReplyEntity.getNote()));
    	dto.setReplyDTO(replyService.convertToDTO(noteReplyEntity.getReply()));
        return dto;
    }

    // DTO -> VO 변환 메소드
    public NoteReplyVO convertToVO(NoteReplyDTO noteReplyDTO) {
    	if (noteReplyDTO == null) {
    		System.out.println("NoteReplyDTO to NoteReplyVO failed. Empty NoteReplyVO created. NoteReplyDTO is null");   	
    		return new NoteReplyVO.Builder().build();
    	}
        return new NoteReplyVO.Builder()
                .noteVO(noteService.convertToVO(noteReplyDTO.getNoteDTO()))
                .replyVO(replyService.convertToVO(noteReplyDTO.getReplyDTO()))
                .build();
    }
}
