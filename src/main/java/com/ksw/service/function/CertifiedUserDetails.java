package com.ksw.service.function;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ksw.vo.forObject.entity.UserVO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CertifiedUserDetails implements UserDetails {

    private UserVO userVO;
    private String password;

    public CertifiedUserDetails(UserVO userVO, String password) {
        this.userVO = userVO;
        this.password = password;
        System.out.println("CertifiedUserDetails created for userVO: " + userVO);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (userVO.getType() == 1) {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        } else if (userVO.getType() == 2) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        System.out.println("Authorities for user " + userVO.getUserId() + ": " + authorities);
        return authorities;
    }

    @Override
    public String getPassword() {
        return password; // User 객체에서 가져온 비밀번호를 반환
    }

    @Override
    public String getUsername() {
        return userVO.getUserId();
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
        return userVO.getIsActive();
    }

    public UserVO getUserVO() {
        return userVO;
    }
}
