package com.TimeVenture.model.enums;

import java.util.Map;

public enum RegistrationId {
    GOOGLE {
        @Override
        public OAuth2UserInfo getOAuth2UserInfo(Map<String, Object> attributes, RegistrationId registrationId) {
            return new GoogleUserInfo(attributes, registrationId);
        }
    },
    NAVER {
        @Override
        public OAuth2UserInfo getOAuth2UserInfo(Map<String, Object> attributes, RegistrationId registrationId) {
            return new NaverUserInfo(attributes, registrationId);
        }
    };

    public abstract OAuth2UserInfo getOAuth2UserInfo(Map<String, Object> attributes, RegistrationId registrationId);
}
