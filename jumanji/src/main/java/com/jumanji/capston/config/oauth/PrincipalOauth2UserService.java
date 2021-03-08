package com.jumanji.capston.config.oauth;

import com.jumanji.capston.config.auth.PrincipalDetails;
import com.jumanji.capston.config.oauth.provider.FacebookUserInfo;
import com.jumanji.capston.config.oauth.provider.GoogleUserInfo;
import com.jumanji.capston.config.oauth.provider.NaverUserInfo;
import com.jumanji.capston.config.oauth.provider.OAuth2UserInfo;
import com.jumanji.capston.data.User;
import com.jumanji.capston.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.sound.midi.Soundbank;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepository userRepository;

    // oauth2 요청으로부터 받은 userRequest 데이터에 대한 후처리 메서드
    // 함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("userRequest : " + userRequest);
        System.out.println("userRequest.getAccessToken : " + userRequest.getAccessToken());
        System.out.println("userRequest.getClientRegistration().getRegistrationId : " + userRequest.getClientRegistration().getRegistrationId());

        // 구글 로그인 버튼 클릭 -> 구글 로그인창 -> 로그인을 완료 -> code를 리턴(OAuth-Client 라이브러리) -> AceessToken 요청
        // userRequest 정보 > loadUser 함수 호출 -> 회원프로필을 받음.

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("getAttributes : " + oAuth2User.getAttributes());

        OAuth2UserInfo oAuth2UserInfo = null;


        String _provider = userRequest.getClientRegistration().getRegistrationId();
        if(_provider.equals("google")) {
            System.out.println("구글 로그인 요청");
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }
        else if(_provider.equals("facebook")){
            System.out.println("페이스북 로그인 요청");
            oAuth2UserInfo = new FacebookUserInfo(oAuth2User.getAttributes());
        }
        else if(_provider.equals("naver")){
            System.out.println("네이버 로그인 요청");
            // 네이버는 response 안에 response가 있고 그 안에 id, name 등이 있다.
            oAuth2UserInfo = new NaverUserInfo((Map)oAuth2User.getAttributes().get("response"));
        }
        else{
            System.out.println("지원안하는 소셜 로그인");
        }
        String provider = oAuth2UserInfo.getProvider(); // google
        String providerId = oAuth2UserInfo.getProviderId();
        String id = provider + "_" + providerId; // google_{sub}
        String name = oAuth2UserInfo.getName();
        String password = bCryptPasswordEncoder.encode("주만지");
        String email = oAuth2UserInfo.getEmail();
        String phone = oAuth2UserInfo.getPhone();
        String role = "ROLE_USER";

        Optional<User> userEntity = userRepository.findById(id);
        if(userEntity.isEmpty()){ // 빈 값 회원가입 진행.
            userEntity = Optional.ofNullable(User.builder()
                    .id(id)
                    .name(name)
                    .pw(password)
                    .role(role)
                    .email(email)
                    .provider(provider)
                    .provider_id(providerId)
                    .sign_date(new Date())
                    .phone(phone)
                    .build());
            userRepository.save(userEntity.get());
        }else{ // 값이 있음 아이디 중복 오류

        }
        // 위의 정보를 토대로 회원가입 강제 진행.

        return new PrincipalDetails(userEntity.get(), oAuth2User.getAttributes());
    }
}
