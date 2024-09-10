package com.TimeVenture.model.entity.member.service;

import com.TimeVenture.model.entity.member.entity.Member;
import com.TimeVenture.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
/*
    UserDetailsService 인터페이스 : 스프링 시큐리티의 사용자 세부 정보 서비스를 정의합니다.
    loadUserByName: 주어진 이메일로 사용자 정보를 DB에서 조회하고, 해당 사용자가 없을 경우 UsernameNotFoundException을 던집니다.
    Member 엔티티가 userDetails 인터페이스를 구현하고 있으므로 바로 반환할 수 있습니다.
 */
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //이메일을 기준으로 사용자를 DB에서 조회
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("해당 이메일로 사용자를 찾을 수 없어요."));

        return member; //   멤버 엔티티가 UserDetails 인터페이스를 구현하고 있으므로 반환 가능
    }

    /*
        - 토큰의 만료 및 보안 문제
            - 만료된 토큰 처리
            - 탈취된 토큰 문제
            - 토큰 크기에 따른 성능 문제

            로그인 화면에서 발생할 수 있는 문제점과 예외 처리 방법
                - 네트워크 오류
                - 잘못된 사용자 입력
                - 토큰 만료
                - 서버 오류
                - 브라우저 호환성 문제

     */
}
