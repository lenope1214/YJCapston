//package com.cuteblog.bbs4.service;
//
//import com.cuteblog.bbs4.domain.Member;
//import com.cuteblog.bbs4.mapper.MemberMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//
//@Service
//public class MemberService {
//    @Autowired(required = false)
//    MemberMapper memberMapper;
//
//    // 회원가입
//    public void join(Member member){
//        System.out.println("service join() : " + member.toString());
//        memberMapper.join(member);
//    }
//
//    // 로그인
//    public Member login(Member member){
//        System.out.println("service login() : " + member.toString());
//        System.out.println("service login() return : " + memberMapper.login(member));
//        return memberMapper.login(member);
//    }
//
//    public ArrayList<Member> memberList(){
//        System.out.println("service memberList() : " + memberMapper.memberList());
//        return memberMapper.memberList();
//    }
//
//
//    // 멤버 삭제
//    public void memberDelete(long mno){
//        memberMapper.memberDelete(mno);
//    }
//}
