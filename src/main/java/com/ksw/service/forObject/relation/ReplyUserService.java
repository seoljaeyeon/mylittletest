package com.ksw.service.forObject.relation;

import com.ksw.dao.object.ReplyRepository;
import com.ksw.dao.relation.ReplyUserMapper;
import com.ksw.dto.forObject.object.ReplyDTO;
import com.ksw.dto.forObject.object.UserDTO;
import com.ksw.dto.forObject.relation.ReplyUserDTO;
import com.ksw.object.entity.jpa.Reply;
import com.ksw.object.entity.mybatis.ReplyUser;
import com.ksw.object.vo.object.ReplyVO;
import com.ksw.object.vo.relation.ReplyUserVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReplyUserService {

	@Autowired
	private ReplyUserMapper replyUserMapper;
	
	@Transactional
	public ReplyUserVO writeReplyRelation(ReplyDTO replyDTO, UserDTO userDTO) {
		
		ReplyUser replyUser = replyUserMapper.insert(replyDTO.getReplyNo(), userDTO.getUserNo());
		return this.convertToVO(this.convertToDTO(replyUser));
	}
	
    // Entity -> DTO 변환 메소드
    public ReplyUserDTO convertToDTO(ReplyUser replyUserEntity) {
        return new ReplyUserDTO.Builder()
                .userNo(replyUserEntity.getUserNo())
                .replyNo(replyUserEntity.getReplyNo())
                .build();
    }
    
    public ReplyUser convertToEntity(ReplyUserDTO replyUserDTO) {
    	ReplyUser replyUser = null;
    	replyUser.setReplyNo(replyUserDTO.getReplyNo());
    	replyUser.setUserNo(replyUserDTO.getUserNo());
    	return replyUser;
    }

    // DTO -> VO 변환 메소드
    public ReplyUserVO convertToVO(ReplyUserDTO replyUserDTO) {
        return new ReplyUserVO.Builder()
                .userNo(replyUserDTO.getUserNo())
                .replyNo(replyUserDTO.getReplyNo())
                .build();
    }
}

