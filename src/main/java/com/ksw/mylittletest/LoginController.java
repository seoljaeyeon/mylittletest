package com.ksw.mylittletest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ksw.dto.function.LoginDTO;
import com.ksw.service.forObject.entity.UserService;
import com.ksw.service.function.CertifiedUserDetails;
import com.ksw.vo.forObject.entity.UserVO;

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
	 * 파라미터에 @AuthenticationPrincipal CertifiedUserDetails userinfo 이걸 넣고, userinfo.getUserVO()하면 userVO를 꺼낼 수 있습니다.
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
