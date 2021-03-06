package com.jumanji.capston.config.oauth;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    // oauth2 요청으로부터 받은 userRequest 데이터에 대한 후처리 메서드
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("userRequest : " + userRequest);
        System.out.println("userRequest.getAccessToken : " + userRequest.getAccessToken());
        System.out.println("userRequest.getClientRegistration().getRegistrationId : " + userRequest.getClientRegistration().getRegistrationId());
        System.out.println(super.loadUser(userRequest).getAttributes());
        // 구글 로그인 버튼 클릭 -> 구글 로그인창 -> 로그인을 완료 -> code를 리턴(OAuth-Client 라이브러리) -> AceessToken 요청
        // userRequest 정보 > loadUser 함수 호출 -> 회원프로필을 받음.

        OAuth2User oAuth2User = super.loadUser(userRequest);
        // 위의 정보를 토대로 회원가입 강제 진행.
        return super.loadUser(userRequest);
    }
}
