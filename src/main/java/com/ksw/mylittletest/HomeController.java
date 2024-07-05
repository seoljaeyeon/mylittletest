package com.ksw.mylittletest;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ksw.service.function.AuthService;
import com.ksw.service.function.AuthTranslationService;
import com.ksw.vo.forObject.entity.UserVO;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	AuthService	authService;
	
    @GetMapping("/")
	public String home(Model model) {
	 
        // 현재 인증된 사용자의 이름을 통해 VO 가져옵니다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthTranslationService authTranslationService = (AuthTranslationService) authService.loadUserByUsername(authentication.getName());
        UserVO userVO = authTranslationService.getUserVO();

        // 모델에 사용자 정보를 추가합니다.
        model.addAttribute("user", userVO);

        return "index";
    }
}
