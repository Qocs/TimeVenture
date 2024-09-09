package com.TimeVenture.model.entity.member.controller;

import com.TimeVenture.jwt.JwtTokenProvider;
import com.TimeVenture.model.entity.member.entity.Member;
import com.TimeVenture.model.entity.member.dto.AuthResponse;
import com.TimeVenture.model.entity.member.dto.LoginRequest;
import com.TimeVenture.model.entity.member.dto.RegisterRequest;
import com.TimeVenture.model.entity.member.service.AuthService;
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
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        //이메일 중복 확인
        if (memberRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body(new AuthResponse("해당 이메일은 이미 존재해요."));
        }

        //새로운 사용자 생성 및 저장
        Member member = Member.builder()
                .email(request.getEmail())
                .name(request.getName())
                .pwd(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        memberRepository.save(member);

        return ResponseEntity.ok(new AuthResponse("회원가입을 성공했어요."));
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


    //클라이언트 측 코드에서 응답에서 자동으로 액세스토큰이 만료되었다는것을 감지할 수 있는 axios 인터셉터를 이용해 해당 메서드 자동으로 동작하도록 유도
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody String refreshToken) {
        try {
            String newTokens = authService.refreshAccessToken(refreshToken);
            String[] tokens = newTokens.split(", ");
            String newAccessToken = tokens[0];
            String newRefreshToken = tokens[1];

            // 클라이언트에 새 토큰 반환
            return ResponseEntity.ok(new AuthResponse("새로운 액세스 토큰이 발급되었어요.", newAccessToken, newRefreshToken));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new AuthResponse(e.getMessage()));
        }
    }
}
