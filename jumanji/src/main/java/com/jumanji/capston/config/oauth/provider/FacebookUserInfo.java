package com.jumanji.capston.config.oauth.provider;

import java.util.Map;

public class FacebookUserInfo implements OAuth2UserInfo{

    private Map<String, Object> attributes; // oAuth2User.getAttributes() 속성들 값 보기.

    public FacebookUserInfo(Map<String, Object> attributes){
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        return (String) attributes.get("id");
    }

    @Override
    public String getProvider() {
        return "facebook";
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    public String getPhone(){
        return "";
    }
}
