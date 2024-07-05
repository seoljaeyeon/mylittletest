package com.ksw.service.forObject.entity;

import com.ksw.dao.forObject.entity.UserRepository;
import com.ksw.dto.forObject.entity.UserDTO;
import com.ksw.dto.function.JoinDTO;
import com.ksw.dto.function.LoginDTO;
import com.ksw.object.entity.User;
import com.ksw.vo.forObject.entity.UserVO;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
	
    // DTO -> Entity 변환 메소드
    public User convertToEntity(UserDTO userDTO) {
        User userEntity = new User();
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
        return new UserDTO.Builder()
                .userNo(userEntity.getUserNo())
                .userId(userEntity.getUserId())
                .password(userEntity.getPassword())
                .nickname(userEntity.getNickname())
                .email(userEntity.getEmail())
                .securityQuestion(userEntity.getSecurityQuestion())
                .securityAnswer(userEntity.getSecurityAnswer())
                .isActive(userEntity.getIsActive())
                .type(userEntity.getType())
                .createdAt(userEntity.getCreatedAt())
                .build();
    }

    // DTO -> VO 변환 메소드
    public UserVO convertToVO(UserDTO userDTO) {
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
}
