package com.jumanji.capston.controller;

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
    public String join(@RequestBody Member m){
        System.out.println("join\nm.toString() : " + m.toString() +"\n" +
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
    public String login(@RequestBody Member m){
        Optional<Member> l_member = memberRepository.findById(m.getId());

        System.out.println("login\nm.toString() : " + m.toString() +"\n" +
                "m.getId() : " + m.getId() + "\n" +
                "m.getPw() : " + m.getPw() + "\n" +
                "m.getAuth() : " + m.getAuth()
        );
        System.out.println("l_member.get().getPw() : " +  l_member.get().getPw());
        if(Integer.parseInt(l_member.get().getPw()) == Integer.parseInt(m.getPw())){
            System.out.println("로그인 성공");
            return "success";
        }else{
            System.out.println("로그인 실패");
            return null;
        }

//        return "rest-login.";
    }



    @GetMapping("/test")
    public String hello(){
        return "안녕하세요?";
    }

    @GetMapping("/members")
    public List<Member> Members(){
        List<Member> memberList;
        memberList = memberRepository.findAll();
        return memberList;
    }
}
