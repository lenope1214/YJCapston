package com.jumanji.capston.controller;

import com.jumanji.capston.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/test/login")
    public @ResponseBody String loginTest(Authentication authentication){
        System.out.println("/test/login ==============");
        System.out.println("authentication : " + authentication.getPrincipal());
        return "세션 정보 확인하기";
    }


    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/corsTest")
    public String view() {
        return "/cors";
    }

    @GetMapping("/loginForm")
    public String login(){
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


}
