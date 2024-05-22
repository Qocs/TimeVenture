package com.TimeVenture.member.service;

import com.TimeVenture.member.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleServiceImpl implements GoogleService {

    @Value("${google.client.id}")
    private String googleClientId;

    @Value("${google.client.pw}")
    private String googleClientPW;

    @Value("${google.auth.url}")
    private String googleAuthUrl;

    private final RestTemplate restTemplate;

    public GoogleServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ApiResponse<String> getGoogleLoginView() {
        String googleLoginUrl = "https://accounts.google.com/o/oauth2/auth?client_id=" + googleClientId
                + "&redirect_uri=http://localhost:80/api/v1/google/login&response_type=code&scope=email%20profile%20openid&access_type=offline";
        return new ApiResponse<>(googleLoginUrl);
    }

    @Override
    public ApiResponse<GoogleDetailResponse> loginGoogle(String code) {
        GoogleTokenRequest googleOAuthRequestParam = GoogleTokenRequest.builder()
                .clientId(googleClientId)
                .clientSecret(googleClientPW)
                .code(code)
                .redirectUri("http://localhost:80/api/v1/google/login")
                .grantType("authorization_code")
                .build();

        GoogleTokenResponse tokenResponse = restTemplate.postForObject(googleAuthUrl + "/token", googleOAuthRequestParam, GoogleTokenResponse.class);
        String jwtToken = tokenResponse.getId_token();

        GoogleDetailRequest googleDetailRequest = new GoogleDetailRequest(jwtToken);
        GoogleDetailResponse googleDetailResponse = restTemplate.postForObject(googleAuthUrl + "/tokeninfo", googleDetailRequest, GoogleDetailResponse.class);

        return new ApiResponse<>(googleDetailResponse);
    }
}