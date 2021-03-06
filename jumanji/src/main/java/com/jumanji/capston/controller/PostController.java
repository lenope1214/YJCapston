package com.jumanji.capston.controller;

import com.jumanji.capston.controller.exception.MemberNotFoundException;
import com.jumanji.capston.data.Member;
import com.jumanji.capston.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class PostController {
    @Autowired
    MemberRepository memberRepository;

    @PostMapping("/join")
    public Member join(@RequestBody Member _member) {
        Member m = _member;
        System.out.println("join\nm.toString() : " + m.toString() + "\n" +
                "m.getId() : " + m.getId() + "\n" +
                "m.getPw() : " + m.getPw() + "\n" +
                "m.getName() : " + m.getName() + "\n" +
                "m.getPhone() : " + m.getPhone() + "\n" +
                "m.getAuth() : " + m.getAuth()
        );
        if(memberRepository.findById(m.getId()).isEmpty())
            memberRepository.save(m);

        return m;
    }

//    @Transactional(readOnly = true)
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

//    @Transactional(readOnly = true)
    @GetMapping("/myInfo/{id}")
    public Member myInfo(@PathVariable("id") String id){
        return memberRepository.findById(id)
                .orElseThrow(()-> new MemberNotFoundException(id));
    }

//    @Transactional(readOnly = true)
    @GetMapping("/members")
    public List<Member> Members() {
        List<Member> memberList;
        memberList = memberRepository.findAll();
        return memberList;
    }
}
