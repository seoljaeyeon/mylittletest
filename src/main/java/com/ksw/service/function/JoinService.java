package com.ksw.service.function;

import java.security.SecureRandom;
import java.sql.Timestamp;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ksw.dao.forObject.entity.UserRepository;
import com.ksw.dto.function.JoinDTO;
import com.ksw.object.entity.User;

@Service
public class JoinService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MailService mailService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private HttpSession session;
    
    @Transactional
    public String join(JoinDTO joinDTO) {
    	
    	// 메일 인증 유효성 검증 메소드 추가
    	if (!mailService.mailValidityCheck(joinDTO.getCode())) {
    		return "invalid-code";
    	}
    	
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

        userRepository.save(user);
        return "success";
    }
}
