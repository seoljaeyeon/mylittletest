package com.ksw.service.forObject.entity;

import com.ksw.dao.forObject.entity.ReplyRepository;
import com.ksw.dto.forObject.entity.ReplyDTO;
import com.ksw.object.entity.Reply;
import com.ksw.vo.forObject.entity.ReplyVO;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReplyService {
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@Transactional
	public ReplyVO writeReply(ReplyDTO replyDTO) {
		Reply reply = replyRepository.save(this.convertToEntity(replyDTO));
		return this.convertToVO(this.convertToDTO(reply));
	}
	
	// List<ReplyDTO> -> List<ReplyVO> 변환 메소드
	public List<ReplyVO> convertToVOList(List<ReplyDTO> replyDTOList) {
		return replyDTOList.stream()
				.map(this::convertToVO)
				.collect(Collectors.toList());
	}
	
    // Entity -> DTO 변환 메소드
    public ReplyDTO convertToDTO(Reply replyEntity) {
    	ReplyDTO dto = new ReplyDTO();
    	if (replyEntity == null) {
    		System.out.println("Reply to ReplyDTO failed. Empty ReplyDTO created. Reply is null");
    		return dto;
    	}
    	dto.setCreatedAt(null);
    	dto.setIsActive(null);
    	dto.setParentReply(null);
    	dto.setReplyContent(null);
    	dto.setReplyNo(null);
    	dto.setUpdatedAt(null);
        return dto;
    }
    
    public Reply convertToEntity(ReplyDTO replyDTO) {
    	if(replyDTO == null) {
    		return null;
    	}
    	
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
    	if(replyDTO == null) {
    		return null;
    	}
    	
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
