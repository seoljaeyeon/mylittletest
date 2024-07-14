package com.ksw.service.forObject.relation;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
	public ReplyUserDTO writeReplyRelation(ReplyDTO replyDTO, UserDTO userDTO) {
		ReplyUserDTO replyUserDTO = new ReplyUserDTO();
		if (replyDTO == null || userDTO == null) {
    		System.out.println("Writting ReplyRelation failed. One of parameters is empty. Empty ReplyUserDTO returned");   	
			return replyUserDTO;
		}
		ReplyUser replyUser = replyUserMapper.insert(this.convertToEntity(replyUserDTO));
		replyUserDTO = this.convertToDTO(replyUser);
		return replyUserDTO;
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
    
    // List<ReplyUserDTO> -> List<ReplyUserVO> 변환 메소드
    public List<ReplyUserVO> convertToVOList(List<ReplyUserDTO> replyUserDTOs) {
    	if (replyUserDTOs == null) {
    		System.out.println("List<ReplyUserDTO>  to List<ReplyUserVO> failed. Empty List<ReplyUserVO> created. List<ReplyUserDTO> is null");   	
    		return Collections.singletonList(new ReplyUserVO.Builder().build());
    	}
    	
    	// replyUserDTOs의 각 요소들을 순환하면
        return replyUserDTOs.stream()
        		// 각 요소들에 대해 convertToVO 메소드를 사용한다.
                .map(this::convertToVO)
                // 결과들을 Collectors 클래스를 활용해 하나로 모아서 반환한다.
                .collect(Collectors.toList());
    }
}

