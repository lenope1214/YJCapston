package com.jumanji.capston.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
//    @Autowired
//    AccountRepository accountRepository;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/corsTest")
    public String view() {
        return "/cors";
    }

//    @GetMapping("/login")
//    public String login(){
//        return "login";
//    }
//
//    @GetMapping("/myPoint")
//    public String myPoint(){
//        return "myPoint";
//    }
//
//    @GetMapping("/joinus")
//    public String joinUs(){
//        return "joinus";
//    }
//
//    @GetMapping("/kakaoTest")
//    public String kakaoTest(){
//        return "kakaoApiTest";
//    }


}
