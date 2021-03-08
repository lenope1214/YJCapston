package com.jumanji.capston.config.oauth.provider;

public interface OAuth2UserInfo {
    String getProviderId();  // google_sub facebook_id
    String getProvider(); // google , facebook
    String getEmail();  // email
    String getName();  // 이성복
    String getPhone();
}
