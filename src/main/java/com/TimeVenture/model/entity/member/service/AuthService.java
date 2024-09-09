package com.TimeVenture.model.entity.member.service;

import com.TimeVenture.jwt.JwtTokenProvider;
import com.TimeVenture.model.entity.member.entity.Member;
import com.TimeVenture.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

    public String refreshAccessToken(String refreshToken) {

        //Redis에서 리프레시 토큰 검색
        Optional<Member> memberOptional = memberRepository.findByEmail(jwtTokenProvider.getEmailFromToken(refreshToken));
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            String savedRefreshToken = refreshTokenService.getRefreshToken(member.getEmail());

            if (savedRefreshToken != null && savedRefreshToken.equals(refreshToken)) {
                //리프레시 토큰이 유효하고 일치할 때
                if (jwtTokenProvider.validateToken(refreshToken)) {
                    Authentication authentication = new UsernamePasswordAuthenticationToken(member.getEmail(), member.getPwd());
                    String newAccessToken = jwtTokenProvider.createToken(authentication);
                    String newRefreshToken = refreshTokenService.createAndSaveRefreshToken(member);

                    return newAccessToken + ", " + newRefreshToken;
                }
            }
        }
        throw new IllegalArgumentException("유효하지 않은 리프레시 토큰입니다.");
    }
}
