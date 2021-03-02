package com.jumanji.capston.controller;

import com.jumanji.capston.data.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/rest")
public class PostController {
//    @Autowired
//    AccountRepository accountRepository;


    @PostMapping("/join")
    public String index(@RequestBody Member m){
        System.out.println("m.toString() : " + m.toString() +"\n" + "m.getId() : " + m.getId());
        return "rest-join입니다."; 
    }

    @GetMapping("/test")
    public String hello(){
        return "안녕하세요?";
    }


    
}
