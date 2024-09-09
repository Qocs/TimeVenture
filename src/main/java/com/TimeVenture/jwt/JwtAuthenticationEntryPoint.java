package com.TimeVenture.jwt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //인증되지 않은 사용자가 접근할 때 401 상태와 에러 메시지 반환
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "인증 토큰이 없거나 유효하지 않아요.");
    }
}
