package com.ksw.service.forObject.object;

import com.ksw.dto.forObject.object.ReplyDTO;
import com.ksw.object.entity.jpa.Reply;
import com.ksw.object.vo.object.ReplyVO;

import org.springframework.stereotype.Service;

@Service
public class ReplyService {

    // Entity -> DTO 변환 메소드
    public ReplyDTO convertToDTO(Reply replyEntity) {
        return new ReplyDTO.Builder()
                .replyNo(replyEntity.getReplyNo())
                .replyContent(replyEntity.getReplyContent())
                .parentReply(replyEntity.getParentReply())
                .isActive(replyEntity.getIsActive())
                .createdAt(replyEntity.getCreatedAt())
                .updatedAt(replyEntity.getUpdatedAt())
                .build();
    }
    
    public Reply convertToEntity(ReplyDTO replyDTO) {
        Reply replyEntity = new Reply();
        replyEntity.setReplyNo(replyDTO.getReplyNo());
        replyEntity.setReplyContent(replyDTO.getReplyContent());
        replyEntity.setParentReply(replyDTO.getParentReply());
        replyEntity.setIsActive(replyDTO.getIsActive());
        replyEntity.setCreatedAt(replyDTO.getCreatedAt());
        replyEntity.setUpdatedAt(replyDTO.getUpdatedAt());
        return replyEntity;
    }

    // DTO -> VO 변환 메소드
    public ReplyVO convertToVO(ReplyDTO replyDTO) {
        return new ReplyVO.Builder()
                .replyNo(replyDTO.getReplyNo())
                .replyContent(replyDTO.getReplyContent())
                .parentReply(replyDTO.getParentReply())
                .isActive(replyDTO.getIsActive())
                .createdAt(replyDTO.getCreatedAt())
                .updatedAt(replyDTO.getUpdatedAt())
                .build();
    }
}
