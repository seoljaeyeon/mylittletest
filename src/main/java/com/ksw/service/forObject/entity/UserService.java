package com.ksw.service.forObject.entity;

import org.springframework.stereotype.Service;

import com.ksw.dto.forObject.entity.UserDTO;
import com.ksw.object.entity.User;
import com.ksw.vo.forObject.entity.UserVO;

@Service
public class UserService {
	
    // DTO -> Entity 변환 메소드
    public User convertToEntity(UserDTO userDTO) {
        User userEntity = new User();
        if (userDTO == null) {
    		System.out.println("UserDTO to User failed. Empty User created. UserDTO is null");
        	return userEntity;
        }
        userEntity.setUserNo(userDTO.getUserNo());
        userEntity.setUserId(userDTO.getUserId());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setNickname(userDTO.getNickname());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setSecurityQuestion(userDTO.getSecurityQuestion());
        userEntity.setSecurityAnswer(userDTO.getSecurityAnswer());
        userEntity.setIsActive(userDTO.getIsActive());
        userEntity.setType(userDTO.getType());
        userEntity.setCreatedAt(userDTO.getCreatedAt());
        return userEntity;
    }
	
    // Entity -> DTO 변환 메소드
    public UserDTO convertToDTO(User userEntity) {
        UserDTO dto = new UserDTO();
        if (userEntity == null) {
    		System.out.println("User to UserDTO failed. Empty UserDTO created. User is null");
        	return dto;        	
        }
        dto.setUserNo(userEntity.getUserNo());
        dto.setUserId(userEntity.getUserId());
        dto.setPassword(userEntity.getPassword());
        dto.setNickname(userEntity.getNickname());
        dto.setEmail(userEntity.getEmail());
        dto.setSecurityQuestion(userEntity.getSecurityQuestion());
        dto.setSecurityAnswer(userEntity.getSecurityAnswer());
        dto.setIsActive(userEntity.getIsActive());
        dto.setType(userEntity.getType());
        dto.setCreatedAt(userEntity.getCreatedAt());
        return dto;
    }

    // DTO -> VO 변환 메소드
    public UserVO convertToVO(UserDTO userDTO) {
    	System.out.println("UserDTO to UserVO failed. Empty UserVO created. UserDTO is null");
    	if(userDTO == null) {
    		return new UserVO.Builder().build();
    	}
        return new UserVO.Builder()
                .userNo(userDTO.getUserNo())
                .userId(userDTO.getUserId())
                .nickname(userDTO.getNickname())
                .email(userDTO.getEmail())
                .isActive(userDTO.getIsActive())
                .type(userDTO.getType())
                .createdAt(userDTO.getCreatedAt())
                .build();
    }
    
    // VO -> DTO 변환 메소드
    public UserDTO convertVOToDTO(UserVO userVO) {
    	UserDTO dto = new UserDTO();
    	if (userVO == null) {
    		System.out.println("UserVO to UserDTO failed. Empty UserDTO created. UserVO is null");
    		return dto;
    	}
    	dto.setUserNo(userVO.getUserNo());
    	dto.setUserId(userVO.getUserId());
    	dto.setNickname(userVO.getNickname());
    	dto.setEmail(userVO.getEmail());
    	dto.setIsActive(userVO.getIsActive());
    	dto.setType(userVO.getType());
    	dto.setCreatedAt(userVO.getCreatedAt());
    	return dto;
    }
}
