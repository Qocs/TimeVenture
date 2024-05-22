package com.TimeVenture.member.controller;

import com.TimeVenture.member.model.GoogleTokenRequest;
import com.TimeVenture.member.model.GoogleTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/oauth2/google")
public class GoogleLoginController {

    @Value("${google.client.id}")
    private String googleClientId;

    @Value("${google.client.pw}")
    private String googleClientPW;

    private final RestTemplate restTemplate;

    public GoogleLoginController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/url")
    public String loginUrlGoogle() {
        return "https://accounts.google.com/o/oauth2/auth?client_id=" + googleClientId
                + "&redirect_uri=http://localhost/loginAccount&response_type=code&scope=email%20profile%20openid&access_type=offline";
    }

    @GetMapping
    public ResponseEntity<String> loginGoogle(@RequestParam(value="code") String authCode) {
        try {
            GoogleTokenRequest googleOAuthRequestParam = GoogleTokenRequest.builder()
                    .clientId(googleClientId)
                    .clientSecret(googleClientPW)
                    .code(authCode)
                    .redirectUri("http://localhost:80/api/v1/oauth2/google")
                    .grantType("authorization_code")
                    .build();

            ResponseEntity<GoogleTokenResponse> resultEntity = restTemplate.postForEntity(
                    "https://oauth2.googleapis.com/token",
                    googleOAuthRequestParam,
                    GoogleTokenResponse.class
            );

            String jwtToken = resultEntity.getBody().getId_token();
            String tokenInfoUrl = "https://oauth2.googleapis.com/tokeninfo?id_token=" + jwtToken;
            ResponseEntity<Map> resultEntity2 = restTemplate.getForEntity(tokenInfoUrl, Map.class);

            String email = (String) resultEntity2.getBody().get("email");
            return ResponseEntity.ok(email);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Failed to login with Google: " + e.getMessage());
        }
    }
}