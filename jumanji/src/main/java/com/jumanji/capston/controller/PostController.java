package com.jumanji.capston.controller;

import com.jumanji.capston.data.Member;
import com.jumanji.capston.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class PostController {
//    @Autowired
//    AccountRepository accountRepository;
    @Autowired
    MemberRepository memberRepository;

    @PostMapping("/join")
    public Member join(@RequestBody Member m){
        System.out.println("JOIN - member : " + m.toString());
        Member member = new Member();
        member.setId(m.getId());
        System.out.println(member.getId());
        memberRepository.save(m);

        return member;
    }


    
}
