package com.jumanji.capston.controller.commons;

import com.jumanji.capston.config.auth.PrincipalDetails;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;

@Controller
public class WebController {

    @PostMapping("/loginForm")
    public String login(){
        return "loginForm";
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/test/login")
    public @ResponseBody String testLogin(
//            Authentication authentication, // DI(의존성 주입) 1번 방식
            @AuthenticationPrincipal PrincipalDetails userDetails // 2번 방식.
            ){
        System.out.println("/test/login ==============");
//        System.out.println("authentication : " + principalDetails.getUser());

        System.out.println("userDetails : " + userDetails.getUser());
        return "세션 정보 확인하기";
    }

    @GetMapping("/test/oauth/login")
    public @ResponseBody String testOAuthLogin(
            Authentication authentication // DI(의존성 주입) 1번 방식
//            ,  @AuthenticationPrincipal PrincipalDetails userDetails // 2번 방식
            , @AuthenticationPrincipal OAuth2User oAuth
    ){


        System.out.println("/test/login ==============");
        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        System.out.println("authentication : " + oAuth2User.getAttributes());
        System.out.println("oauth2User : " + oAuth.getAttributes());

        return "OAuth세션 정보 확인하기";
    }

    // OAUTH 로그인을 해도 PrincipalDetails 로 받을 수 있고
    // 일반 로그인을 해도 PrincipalDetails 로그인으로 받을 수 있다.
    @GetMapping("/user")
    public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principalDetails){
        System.out.println("principalDetails : " + principalDetails.getUser());
        return "user";
    }

    @GetMapping("/corsTest")
    public String view() {
        return "/cors";
    }

    @GetMapping(value = {"/loginForm"})
    public String loginForm(){
        return "loginForm";
    }

    @GetMapping("/myPoint")
    public String myPoint(){
        return "myPoint";
    }
//
    @GetMapping("/joinus")
    public String joinUs(){
        return "joinus";
    }

    @GetMapping("/denied")
    public @ResponseBody String denied(){
        return "denied!";
    }

   // 여러개 설정 예시
//    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
//    public void update(Contact contact);
    @Secured("ROLE_ADMIN") // 간단하게 admin 권한만 가능하도록 설정함.
    @GetMapping("/info")
    public @ResponseBody String SecuredTest(){
        return "myInfo!";
    }

    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_ADMIN')" )
    @GetMapping("/data")
    public @ResponseBody String preAuthTest(){
        return "myData!";
    }

    @GetMapping("/kakaoTest")
    public String kakaoTest(){
        return "kakaoApiTest";
    }

    @GetMapping("/uploadForm")
    public String uploadForm(){ return "uploadForm";}

    @GetMapping("/loadFile")
    public String loadFile(){return "loadFile";}

    @GetMapping("daumAddr")
    public String daumAddr(){ return "daumAddr";}

    @Transactional
    @GetMapping("/androidPayment")
    public String imptAndroidPayment(){
        return "iamportAndroidPayment";
    }

    @GetMapping("/androidPayComple")
    public String imptAndroidPayComple(){ return "iamportAndroidPayComple";}
}
