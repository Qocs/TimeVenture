package com.TimeVenture.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /*
    JWT 기반 인증을 처리하기 위해 필요한 필터. 이 클래스는 요청 헤더에서 JWT를 추출하고, 이를 사용하여 인증을 수행하는 역할을 가진다. 따라서 직접 구현해야 하는 클래스.
    이 필터는 요청이 들어올 때마다 JWT 토큰을 검사하고, 유효한 토큰이면 인증 객체를 생성하여 스프링 시큐리티 컨텍스트에 저장한다.
     */
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = resolveToken(request); // 요청에서 JWT 토큰을 추출한다.

        if (token != null && jwtTokenProvider.validateToken(token)) { // 토큰이 존재하고 유효하다면
            String username = jwtTokenProvider.getUsernameFromToken(token); // 토큰으로부터 사용자이름을 추출한다.
            UserDetails userDetails = userDetailsService.loadUserByUsername(username); // 그리고 사용자 정보를 로드한다.

            if (userDetails != null) { // 사용자 정보가 유효하다면
                // 스프링 시큐리티 컨텍스트에 인증 설정
                JwtAuthenticationToken authentication = new JwtAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response); // 다음 필터로 요청 전달
    }

    // 요청 헤더에서 JWT 토큰을 추출하는 메서드
    // 요청 헤더에서 Authorization 헤더를 읽어 JWT 토큰을 추출한다. 토큰이 Bearer 로 시작하는지 확인하고, 앞의 Bearer 를 제거한 실제 토큰 값을 반환.
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
