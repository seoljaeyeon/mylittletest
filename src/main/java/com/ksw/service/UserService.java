package com.ksw.service;

import com.ksw.dao.UserRepository;
import com.ksw.dto.forObject.UserDTO;
import com.ksw.dto.forUtil.JoinDTO;
import com.ksw.dto.forUtil.LoginDTO;
import com.ksw.object.entity.jpa.User;
import com.ksw.object.vo.UserVO;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public UserVO login(LoginDTO loginDTO) {
		User user = userRepository.findByUserIdAndPassword(loginDTO.getUserId(), loginDTO.getPassword());
		
		if (user != null && user.getPassword().equals(loginDTO.getPassword()))  {
			return this.convertToVO(this.convertToDTO(user));
		} else {
			return null;
		}
	}
	
	public void join(JoinDTO joinDTO) {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        userRepository.join(
                joinDTO.getUserId(),
                joinDTO.getPassword(),
                joinDTO.getNickname(),
                joinDTO.getEmail(),
                joinDTO.getSecurityQuestion(),
                joinDTO.getSecurityAnswer(),
                true, // isActive
                1, // type
                currentTimestamp
        );
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
                .password(userDTO.getPassword())
                .nickname(userDTO.getNickname())
                .email(userDTO.getEmail())
                .securityQuestion(userDTO.getSecurityQuestion())
                .securityAnswer(userDTO.getSecurityAnswer())
                .isActive(userDTO.getIsActive())
                .type(userDTO.getType())
                .createdAt(userDTO.getCreatedAt())
                .build();
    }
}
