package com.ksw.service.object;

import com.ksw.dto.forObject.NoteReplyDTO;
import com.ksw.object.entity.mybatis.NoteReply;
import com.ksw.object.vo.NoteReplyVO;
import org.springframework.stereotype.Service;

@Service
public class NoteReplyService {

    // Entity -> DTO 변환 메소드
    public NoteReplyDTO convertToDTO(NoteReply noteReplyEntity) {
        return new NoteReplyDTO.Builder()
                .noteNo(noteReplyEntity.getNoteNo())
                .replyNo(noteReplyEntity.getReplyNo())
                .build();
    }

    // DTO -> VO 변환 메소드
    public NoteReplyVO convertToVO(NoteReplyDTO noteReplyDTO) {
        return new NoteReplyVO.Builder()
                .noteNo(noteReplyDTO.getNoteNo())
                .replyNo(noteReplyDTO.getReplyNo())
                .build();
    }
}
