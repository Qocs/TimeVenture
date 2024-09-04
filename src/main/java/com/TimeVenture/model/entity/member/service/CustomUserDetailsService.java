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

        return member; //멤버 엔티티가 UserDetails 인터페이스를 구현하고 있으므로 반환 가능
    }
}
