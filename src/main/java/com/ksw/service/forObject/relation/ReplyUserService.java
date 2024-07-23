package com.ksw.service.forObject.relation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ksw.dao.forObject.relation.ReplyUserMapper;
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
	
	public Reply insertReply(Reply reply) {
		
		return replyUserMapper.insertReply(reply); 
	}
	
	public List<Map<String, Object>> getRepliesByNoteNo(Integer noteNo) {
		List<Map<String, Object>> result = new ArrayList<>();
		if(noteNo == null) {
			return result;
		}
		result = replyUserMapper.getRepliesAndWriterByNoteNo(noteNo);
	    return result; 
	}
	
	@Transactional
	public void writeReplyRelation(Integer replyNo, Integer userNo) {
		ReplyUserDTO replyUserDTO = new ReplyUserDTO();
		if (replyNo == null || userNo == null) {
    		System.out.println("Writting ReplyRelation failed. One of parameters is empty. Empty ReplyUserDTO returned");   	
		}
		replyUserMapper.insert(replyNo, userNo);
		System.out.println("replyUser insert  성공 결과값 확인 ");
	}
	
    // Entity -> DTO 변환 메소드
    public ReplyUserDTO convertToDTO(ReplyUser replyUserEntity) {
    	ReplyUserDTO dto = new ReplyUserDTO();
    	if (replyUserEntity == null) {
    		System.out.println("ReplyUserDTO to ReplyUser failed. Empty ReplyUser created. ReplyUserDTO is null");   	
    		return dto;
    	}
    	dto.setReplyDTO(replyService.convertToDTO(replyUserEntity.getReply()));
    	dto.setUserDTO(userService.convertToDTO(replyUserEntity.getUser()));
        return dto;
    }
    
    // DTO -> Entity 변환 메소드
    public ReplyUser convertToEntity(ReplyUserDTO replyUserDTO) {
        ReplyUser replyUserEntity = new ReplyUser();
        if (replyUserDTO == null) {
    		System.out.println("ReplyUser to ReplyUserDTO failed. Empty ReplyUserDTO created. ReplyUser is null");   	
        	return replyUserEntity;
        }
        Reply replyEntity = replyService.convertToEntity(replyUserDTO.getReplyDTO());
        User userEntity = userService.convertToEntity(replyUserDTO.getUserDTO());

        replyUserEntity.setReply(replyEntity);
        replyUserEntity.setUser(userEntity);

        return replyUserEntity;
    }

    // DTO -> VO 변환 메소드
    public ReplyUserVO convertToVO(ReplyUserDTO replyUserDTO) {
    	if (replyUserDTO == null) {
    		System.out.println("ReplyUserDTO to ReplyUserVO failed. Empty ReplyUserVO created. ReplyUserDTO is null");   	
    		return new ReplyUserVO.Builder().build();
    	}
        return new ReplyUserVO.Builder()
                .userVO(userService.convertToVO(replyUserDTO.getUserDTO()))
                .replyVO(replyService.convertToVO(replyUserDTO.getReplyDTO()))
                .build();
    }
}

