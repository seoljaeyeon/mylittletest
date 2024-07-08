package com.ksw.service.function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.ksw.service.forObject.entity.UserService;
import com.ksw.object.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();
        AuthTranslationService authTranslationService = (AuthTranslationService) authentication.getPrincipal();
        User user = authTranslationService.getUser();
        session.setAttribute("userVO", userService.convertToVO(userService.convertToDTO(user)));

        response.sendRedirect(request.getContextPath() + "/");
    }
}
