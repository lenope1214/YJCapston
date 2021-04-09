package com.jumanji.capston.controller;

import com.jumanji.capston.data.Member;
import com.jumanji.capston.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class PostController {
    @Autowired
    MemberRepository memberRepository;

    @PostMapping("/join")
    public String index(@RequestBody Member m){
        System.out.println("m.toString() : " + m.toString() +"\n" + "m.getId() : " + m.getId());
        return "rest-join입니다.";
    }

    @GetMapping("/test")
    public String hello(){
        return "안녕하세요?";
    }

    @GetMapping("/getMembers")
    public List<Member> getMembers(){
        List<Member> memberList;
        memberList = memberRepository.findAll();
        return memberList;
    }
    
}
