package com.TimeVenture.member.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class GoogleTokenRequest {
    private String clientId;
    private String clientSecret;
    private String code;
    private String redirectUri;
    private String grantType;
}
