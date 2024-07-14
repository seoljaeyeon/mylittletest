package com.ksw.service.function;

import com.ksw.dao.forObject.entity.UserRepository;
import com.ksw.object.entity.User;
import com.ksw.service.forObject.entity.UserService;
import com.ksw.vo.forObject.entity.UserVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
   
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        System.out.println("Trying to load user by username: " + userId);
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            System.out.println("User not found: " + userId);
            throw new UsernameNotFoundException("사용자없음");
        }
        UserVO userVO = userService.convertToVO(userService.convertToDTO(user));
        System.out.println("User found: " + user);
        System.out.println("UserVO: " + userVO);
        return new CertifiedUserDetails(userVO, user.getPassword());
    }
    
    public UserVO getUserVO() {
        // 현재 인증된 사용자 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            // 인증되지 않은 사용자 요청 처리
            return null; // 또는 기본 값 반환, 예외 던지기 등 적절한 처리를 수행
        }
        CertifiedUserDetails userDetails = (CertifiedUserDetails) authentication.getPrincipal();
        // 인증된 사용자 정보에서 UserVO 객체를 가져옴
        return userDetails.getUserVO();
    }
}
