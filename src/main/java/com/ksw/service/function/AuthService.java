package com.ksw.service.function;

import com.ksw.dao.UserRepository;
import com.ksw.object.entity.jpa.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Trying to load user by username: " + username);
        User user = userRepository.findByUserId(username);
        if (user == null) {
            System.out.println("User not found: " + username);
            throw new UsernameNotFoundException("사용자없음");
        }
        System.out.println("User found: " + user);
        return new AuthTranslationService(user);
    }
}
