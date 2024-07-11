package com.ksw.service.forObject.relation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ksw.dao.forObject.relation.ReplyUserMapper;
import com.ksw.dto.forObject.entity.ReplyDTO;
import com.ksw.dto.forObject.entity.UserDTO;
import com.ksw.dto.forObject.relation.ReplyUserDTO;
import com.ksw.object.entity.Reply;
import com.ksw.object.entity.User;
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
	@Autowired
	private ReplyUserMapper replyUserMapper;
	@Autowired
	private ReplyUserService replyUserService;
	
	
	@Transactional
	public void writeReplayRelation(ReplyDTO replyDTO, UserDTO userDTO) {
		ReplyUserDTO replyUserDTO = new ReplyUserDTO(replyDTO, userDTO);
		replyUserMapper.insert(this.convertToEntity(replyUserDTO));
	}
	
    // Entity -> DTO 변환 메소드
    public ReplyUserDTO convertToDTO(ReplyUser replyUserEntity) {
    	ReplyUserDTO dto = new ReplyUserDTO();
    	dto.setReplyDTO(replyService.convertToDTO(replyUserEntity.getReply()));
    	dto.setUserDTO(userService.convertToDTO(replyUserEntity.getUser()));
        return dto;
    }
    
    // DTO -> Entity 변환 메소드
    public ReplyUser convertToEntity(ReplyUserDTO replyUserDTO) {
        ReplyUser replyUserEntity = new ReplyUser();

        Reply replyEntity = replyService.convertToEntity(replyUserDTO.getReplyDTO());
        User userEntity = userService.convertToEntity(replyUserDTO.getUserDTO());

        replyUserEntity.setReply(replyEntity);
        replyUserEntity.setUser(userEntity);

        return replyUserEntity;
    }

    // DTO -> VO 변환 메소드
    public ReplyUserVO convertToVO(ReplyUserDTO replyUserDTO) {
        return new ReplyUserVO.Builder()
                .userVO(userService.convertToVO(replyUserDTO.getUserDTO()))
                .replyVO(replyService.convertToVO(replyUserDTO.getReplyDTO()))
                .build();
    }
}

