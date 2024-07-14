package com.ksw.mylittletest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ksw.service.forObject.entity.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	@GetMapping("/login")
	public String toLoginPage(HttpSession session) {
		
        // 현재 인증된 사용자 정보 가져오기
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            // 사용자 정보가 존재하면 index 페이지로 리디렉션
            return "redirect:/index";
        } else {
            // 사용자 정보가 존재하지 않으면 login 페이지로 이동
            return "login";
        }
	}
	
	/*
	 * 로그인 기능은 Spring Security를 통해서 구현했습니다. 
	 * 
	 * 인증이 필요한 페이지에 authService를 @Autowired하시고, UserVO userVO = authService.getUserVO()  로 사용자 정보를 얻고 점검하시기 바랍니다.
	 * 
	 * 모든 form이 csrf 기능이 활성화 되어있기 때문에 csrf 태그를 각 폼에 추가해야 합니다.
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
