package com.ksw.service.function;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;

import com.ksw.object.entity.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AuthTranslationService implements UserDetails {
	
    private User user;

    public AuthTranslationService(User user) {
        this.user = user;
        System.out.println("CustomUserDetails created for user: " + user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user.getType() == 1) {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        } else if (user.getType() == 2) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        System.out.println("Authorities for user " + user.getUserId() + ": " + authorities);
        return authorities;
    }
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    public User getUser() {
    	return this.user;
    }
    
    @Override
    public String getUsername() {
        return user.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getIsActive();
    }
}
