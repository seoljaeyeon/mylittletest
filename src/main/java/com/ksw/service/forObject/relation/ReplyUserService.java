package com.ksw.service.forObject.relation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dto.forObject.relation.ReplyUserDTO;
import com.ksw.object.relation.ReplyUser;
import com.ksw.service.forObject.entity.ReplyService;
import com.ksw.service.forObject.entity.UserService;
import com.ksw.vo.forObject.relation.ReplyUserVO;

@Service
public class ReplyUserService {

	@Autowired
	private ReplyService replyService;
	@Autowired
	private UserService userService;
	
    // Entity -> DTO 변환 메소드
    public ReplyUserDTO convertToDTO(ReplyUser replyUserEntity) {
    	ReplyUserDTO dto = new ReplyUserDTO();
    	dto.setReplyDTO(replyService.convertToDTO(replyUserEntity.getReply()));
    	dto.setUserDTO(userService.convertToDTO(replyUserEntity.getUser()));
        return dto;
    }

    // DTO -> VO 변환 메소드
    public ReplyUserVO convertToVO(ReplyUserDTO replyUserDTO) {
        return new ReplyUserVO.Builder()
                .userVO(userService.convertToVO(replyUserDTO.getUserDTO()))
                .replyVO(replyService.convertToVO(replyUserDTO.getReplyDTO()))
                .build();
    }
}

