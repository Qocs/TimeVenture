package com.TimeVenture.model.entity.member.controller;

import com.TimeVenture.jwt.JwtTokenProvider;
import com.TimeVenture.model.entity.member.entity.Member;
import com.TimeVenture.model.entity.member.dto.AuthResponse;
import com.TimeVenture.model.entity.member.dto.LoginRequest;
import com.TimeVenture.model.entity.member.dto.RegisterRequest;
import com.TimeVenture.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        //이메일 중복 확인
        if (memberRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body(new AuthResponse("해당 이메일은 이미 존재해요.", null));
        }

        //새로운 사용자 생성 및 저장
        Member member = Member.builder()
                .email(request.getEmail())
                .name(request.getName())
                .pwd(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        memberRepository.save(member);

        return ResponseEntity.ok(new AuthResponse("회원가입을 성공했어요.", null));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        // 사용자의 인증 정보 확인
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        //인증 성공 시 JWT 토큰 생성
        String jwtToken = jwtTokenProvider.createToken(authentication);

        //사용자 정의 응답 객체 반환
        return ResponseEntity.ok(new AuthResponse("로그인에 성공했어요.", jwtToken));
    }
}
