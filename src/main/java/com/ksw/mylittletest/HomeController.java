package com.ksw.mylittletest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ksw.object.entity.User;
import com.ksw.service.function.AuthService;
import com.ksw.service.function.AuthTranslationService;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	AuthService	authService;
	
    @GetMapping("/index")
	public String home(HttpSession session, Model model, HttpServletRequest request) {
    	
	    // CSRF 토큰 확인
	    CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
	    if (csrfToken != null) {
	        System.out.println("CSRF Token in request: " + csrfToken.getToken());
	    } else {
	        System.out.println("CSRF Token not found in request");
	    }
	    
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthTranslationService userDetails = (AuthTranslationService) authentication.getPrincipal();
        User user = userDetails.getUser();

        session.setAttribute("userVO", user);
        model.addAttribute("username", user.getUserId());
    	
        return "index";
    }
}
