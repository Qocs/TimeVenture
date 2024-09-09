package com.TimeVenture.model.entity.member.service;

import com.TimeVenture.model.entity.member.entity.Member;
import com.TimeVenture.model.enums.Role;
import com.TimeVenture.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // OAuth2 사용자 정보 가져오기
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 사용자 정보 추출
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");

        Optional<Member> memberOptional = memberRepository.findByEmail(email);


        // 기존 사용자가 있으면 OAuth2User로 반환
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            return new DefaultOAuth2User(
                    Collections.singleton(new SimpleGrantedAuthority(member.getRole().name())),
                    attributes,
                    userNameAttributeName);
        }

        //새로운 사용자 저장
        Member newMember = saveNewMember(oAuth2User);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(Role.USER.name())),
                attributes,
                userNameAttributeName);
    };

    private Member saveNewMember(OAuth2User oAuth2User) {
        // 필요한 사용자 정보를 추출
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        String imageUrl = oAuth2User.getAttribute("picture");

        Member newMember = Member.builder()
                .email(email)
                .name(name)
                .img(imageUrl)
                .role(Role.USER)
                .regDate(new Timestamp(System.currentTimeMillis()))
                .build();

        //새로운 사용자 정보를 DB에 저장
        return memberRepository.save(newMember);
    }
}
