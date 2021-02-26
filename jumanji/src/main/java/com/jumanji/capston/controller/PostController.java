package com.jumanji.capston.controller;

import com.jumanji.capston.data.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rest")
public class PostController {
//    @Autowired
//    AccountRepository accountRepository;

    @PostMapping("/join")
    public Member index(Member m){
        System.out.println(m);
        return m;
    }


    
}
