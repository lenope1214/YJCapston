package com.jumanji.capston.Controller;

import com.jumanji.capston.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
