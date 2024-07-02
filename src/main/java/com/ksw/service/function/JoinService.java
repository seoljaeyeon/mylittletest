package com.ksw.service.function;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ksw.dao.object.UserRepository;
import com.ksw.dto.forUtil.JoinDTO;
import com.ksw.object.entity.jpa.User;

@Service
public class JoinService {

	@Autowired
	private UserRepository userRepository;
	
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Transactional
    public User join(JoinDTO joinDTO) {
        String encodedPassword = passwordEncoder.encode(joinDTO.getPassword());
        
        User user = new User();
        user.setUserId(joinDTO.getUserId());
        user.setPassword(encodedPassword);
        user.setNickname(joinDTO.getNickname());
        user.setEmail(joinDTO.getEmail());
        user.setSecurityQuestion(joinDTO.getSecurityQuestion());
        user.setSecurityAnswer(joinDTO.getSecurityAnswer());
        user.setIsActive(true);  // 기본값 설정
        user.setType(1);  // 기본값 설정
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));  // 현재 시간 설정
        
        return userRepository.save(user);
    }
}
