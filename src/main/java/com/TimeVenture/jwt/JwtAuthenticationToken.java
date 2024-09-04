package com.TimeVenture.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

    // UserDetails 객체와 사용자 인증 자격 증명(예: JWT 토큰 문자열)을 매개변수로 받는 생성자 사용
    // 권한 목록을 전달하여 사용자 인증에 필요한 권한 정보를 설정한다.
    public JwtAuthenticationToken(UserDetails principal, String credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    //
    public JwtAuthenticationToken(UserDetails principal, String credentials) {
        super(principal, credentials);
    }
}
