package com.TimeVenture.model.entity.member.entity;

import com.TimeVenture.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@ToString
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Member")
public class Member implements UserDetails {

    @Id
    @Column(name = "m_id", unique = true)
    @NotNull
    private String email;

    @Column(name = "m_name")
    @NotNull
    private String name;

    @Column(name = "m_pwd")
    private String pwd;

    @Column(name = "m_img")
    private String img;

    @CreationTimestamp
    @Column(name = "m_regdate", updatable = false)
    @NotNull
    private Timestamp regDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "m_logintype")
    @NotNull
    private LoginType loginType; // 수정된 부분

    @Column(name = "m_token")
    private String refreshToken;

    @Enumerated(EnumType.STRING)
    @Column(name = "m_role")
    private Role role;

    @Builder
    public Member(String email, String name, String pwd, String img, LoginType loginType, String refreshToken, Timestamp regDate, Role role) {
        this.email = email;
        this.name = name;
        this.pwd = pwd;
        this.img = img;
        this.regDate = regDate;
        this.loginType = loginType != null ? loginType : LoginType.LOCAL; // 기본 로그인 타입을 LOCAL로 설정
        this.refreshToken = refreshToken;
        this.role = role != null ? role : Role.USER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return pwd;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

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

    public Member updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }
}
