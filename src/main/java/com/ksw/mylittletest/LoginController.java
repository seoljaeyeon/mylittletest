package com.ksw.mylittletest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ksw.dto.function.LoginDTO;
import com.ksw.service.forObject.entity.UserService;
import com.ksw.vo.forObject.entity.UserVO;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String toLoginPage() {
		return "login";
	}
	
	/*
	 * 로그인 기능은 Spring Security를 통해서 구현했습니다. 
	 * 
	 * 모든 Form의 아래에 이 태그를 넣어주세요.
	 * <sec:csrfInput />
	 * 
	 * 예시)
	 * <form method="post" action="/login">
	 *     <sec:csrfInput />
	 *     <input...  />
	 * </form>
	 * 
	 */
	
}
