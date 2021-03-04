package com.jumanji.capston.controller;

import com.jumanji.capston.controller.exception.MemberNotFoundException;
import com.jumanji.capston.data.Member;
import com.jumanji.capston.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/rest")
public class PostController {
    @Autowired
    MemberRepository memberRepository;

    @PostMapping("/join")
    public String join(@RequestBody Member m) {
        System.out.println("join\nm.toString() : " + m.toString() + "\n" +
                "m.getId() : " + m.getId() + "\n" +
                "m.getPw() : " + m.getPw() + "\n" +
                "m.getName() : " + m.getName() + "\n" +
                "m.getPhone() : " + m.getPhone() + "\n" +
                "m.getAuth() : " + m.getAuth()
        );
        memberRepository.save(m);
        return "rest-join-success";
    }

    @PostMapping("/login")
    public Member login(@RequestBody Member m) {
        System.out.println(">> login");
        return memberRepository.findById(m.getId())
                .orElseThrow(()-> new MemberNotFoundException(m.getId()));
//        return "rest-login.";
    }

    @GetMapping("/memberDelAll")
    public String memberDelAll(){
        memberRepository.deleteAll();
        return "Member 전부 삭제.";
    }


    @GetMapping("/test")
    public String hello() {
        return "안녕하세요?";
    }

    @GetMapping("/members")
    public List<Member> Members() {
        List<Member> memberList;
        memberList = memberRepository.findAll();
        return memberList;
    }
}
