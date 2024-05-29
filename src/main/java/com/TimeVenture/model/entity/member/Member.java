package com.TimeVenture.model.entity.member;
import com.TimeVenture.model.enums.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Member")
public class Member implements UserDetails {

    @Id
    @Column(name = "m_id")
    @NotNull
    private String email; // 이메일을 ID로 사용

    @Column(name = "m_name")
    @NotNull
    private String name;

    @Column(name = "m_pwd")
    private String pwd;

    @Column(name = "m_img")
    private String img;

    @Column(name = "m_regdate")
    @NotNull
    private Timestamp regDate;

    @Column(name = "m_logintype")
    @NotNull
    private String loginType;

    @Column(name = "m_token")
    private String refreshToken;



    @Enumerated(EnumType.STRING)
    @Column(name = "m_role")
    private Role role;

    @Builder
    public Member(String email, String name, String pwd, String img, String loginType, String refreshToken, Role role ) {
        this.email = email;
        this.name = name;
        this.pwd = pwd;
        this.img = img;
        this.loginType = loginType;
        this.refreshToken = refreshToken;
        this.role = role != null ? role : Role.USER; // 기본 역할을 USER로 설정
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return pwd;
    }

    //사용할 아이디
    @Override
    public String getUsername() {
        return email;
    }

    //계정의 유효성
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정의 락 여부
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public Member update(String name, String img, String refreshToken) {
        this.name = name;
        this.img = img;
        this.refreshToken = refreshToken;
        return this;
    }
}
