package com.ksw.mylittletest;

import java.security.SecureRandom;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ksw.service.function.MailService;

@Controller
public class MailController {
	@Autowired
	MailService mailService;
	@Autowired
	HttpSession session;
	
	@GetMapping("/JoinCodeSend")
	@ResponseBody
	public String JoinCodeSend(
			@RequestParam("email") String email) {
			String result = mailService.sendEmail(email);
			return result;
	}
	
    @PostMapping("/CheckCode")
    @ResponseBody
    public String checkCode(@RequestParam("code") String code) {
        Boolean isValid = mailService.mailValidityCheck(code);
        return isValid ? "valid" : "invalid";
    }
}
