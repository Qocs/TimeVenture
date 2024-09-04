package com.TimeVenture.security;

import com.TimeVenture.jwt.JwtAccessDeniedHandler;
import com.TimeVenture.jwt.JwtAuthenticationEntryPoint;
import com.TimeVenture.jwt.JwtAuthenticationFilter;
import com.TimeVenture.jwt.JwtTokenProvider;
import com.TimeVenture.model.entity.member.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    /*
    JwtAuthenticationEntryPoint: 스프링 시큐리티에서 인증되지 않은 사용자가 보호된 리소스에 접근하려고 할 때 발생하는 인증 예외를 처리하는 클래스
     */
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final UserDetailsService userDetailsService;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) //csrf 보호 비활성화. 쿠키를 통한 방식을 사용하지 않기 때문에 굳이 방어할 필요가 없다.
                .cors(cors -> {}) // 기본 CORS 설정을 활성화
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint) // 인증되지 않은 사용자가 접근 시 예외 처리
                        .accessDeniedHandler(jwtAccessDeniedHandler)
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT 인증을 위해 세션을 사용하지 않겠다.
                )
                .authorizeHttpRequests(auth -> auth // URL 패턴에 따른 인증 규칙 정의
                        .requestMatchers("/api/auth/**", "/oauth2/**").permitAll() //인증 없이 접근 가능
                        .anyRequest().authenticated() //나머지 요청은 인증 필요
                )
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("/home")  //로그인 성공 시 리다이렉트 URL
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService) // customOAuth2UserService 설정
                        )
                );

        //JWT 필터를 기본 인증 필터 전에 추가
        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, userDetailsService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
