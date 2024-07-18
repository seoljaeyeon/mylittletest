package com.ksw.mylittletest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ksw.service.function.AuthService;
import com.ksw.service.function.CertifiedUserDetails;
import com.ksw.vo.forObject.entity.UserVO;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	AuthService	authService;
	
    @GetMapping("/index")
	public String home(
			HttpSession session, 
			Model model, 
			HttpServletRequest request) {
    	
    	UserVO userVO = authService.getUserVO();
        
        if (userVO != null) {
            System.out.println("Authenticated user: " + userVO);
            model.addAttribute("userVO", userVO);
        } else {
            System.out.println("No authenticated user found");
        }
        return "index";    	
//	    // CSRF 토큰 확인
//	    CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
//	    if (csrfToken != null) {
//	        System.out.println("CSRF Token in request: " + csrfToken.getToken());
//	    } else {
//	        System.out.println("CSRF Token not found in request");
//	    }
    }
}
