package com.ksw.service.function;

import java.security.SecureRandom;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ksw.exception.MailSendException;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    
    private HttpSession session;

    @Value("${spring.mail.username}")
    private String fromEmail;
    
    public String sendEmail(
    		String email) {
    	
    	String result = "";
    		String mailTitle = "mylittletest 회원가입 인증 코드입니다.";
    		String mailCode = generateRandomCode(6);
    		
    		System.out.print("랜덤코드 생성");
    		
    		session.setAttribute("mailCode", mailCode);
    		session.setAttribute("mailCodeExpire", System.currentTimeMillis() + 300000);

    		System.out.print("세션에 코드 저장");
    		
    		SimpleMailMessage message = new SimpleMailMessage();
    		message.setTo(email);
    		message.setSubject(mailTitle);
    		message.setText("인증번호 :" + mailCode);
    		message.setFrom(fromEmail);
    		
    		System.out.print("메일 세팅");
    		
    		mailSender.send(message);
    		
    		System.out.print("메일 전송");
    		
    		result = "success";
    		return result;
    		
    }
	
    // 랜덤코드 생성 메소드
    private String generateRandomCode(int length){
    	SecureRandom random = new SecureRandom();
    	StringBuilder sb = new StringBuilder(length);
    	for (int i = 0; i< length; i++) {
    		sb.append((char)('0'+random.nextInt(10)));
    	}
    	
    	return sb.toString();
    }
    
    // 메일 인증 유효성 체크 메소드
    public Boolean mailValidityCheck(String code) {
        String mailCode = (String) session.getAttribute("mailCode");
        Long mailCodeExpiry = (Long) session.getAttribute("mailCodeExpire");

        System.out.println("세션이 null");
        
        if (mailCode == null || mailCodeExpiry == null) {
            return false;
        }
        
        System.out.println("이미 지나감");

        if (System.currentTimeMillis() > mailCodeExpiry) {
            session.removeAttribute("mailCode");
            session.removeAttribute("mailCodeExpiry");
            return false;
        }
        
        System.out.println(mailCode.equals(code));
        System.out.println(mailCode);
        System.out.println(code);
        
        
        return mailCode.equals(code);
    }
}
