//package com.cuteblog.bbs4.controller;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@RestController
//public class RestIndexController {
//    // 데이터 처리할 때만 불려짐
//
//    @Autowired
//    com.cuteblog.bbs4.service.MemberService memberService;
//    @Autowired
//    com.cuteblog.bbs4.service.BoardService boardService;
//    @Autowired
//    com.cuteblog.bbs4.service.EmailService emailService;
//
//    // 회원가입
//    @PostMapping("/rest/join")
//    public void join(Member member){
//        memberService.join(member);
////        System.out.println("restController join() : " + );
//    }
//
//    // 로그인
//    @PostMapping("/rest/login")
//    public Member login(Member member, HttpSession httpSession){
//        System.out.println("restController member.toString() : " + member.toString());
//        System.out.println("restController login() return : " + memberService.login(member));
//        httpSession.setAttribute("user", memberService.login(member));
//        return memberService.login(member);
//    }
//
//    // 글작성
//    @PostMapping("/rest/write")
//    public void write(Board board){
//        System.out.println("restController write() : " + board.toString());
//        boardService.write(board);
//    }
//
//
//    // 추천눌름
//    @PutMapping("/rest/reco")
//    public void write(long bno){
////        System.out.println("???");
//        boardService.reco(bno);
//    }
//
//    // 글삭제
//    @DeleteMapping("/rest/boardDelete")
//    public void boardDelete(long bno){
//        boardService.boardDelete(bno);
//    }
//
//    // 글 수정
//    @PutMapping("/rest/update")
//    public void update(Board board){
//        boardService.update(board);
//    }
//
//    // 댓글
//    @PostMapping("/rest/reply")
//    public void reply(Reply reply){
//        boardService.reply(reply);
//    }
//
//    // 댓글 추천
//    @PutMapping("/rest/up")
//    public void up(Reply reply){
//        boardService.up(reply);
//    }
//
//    // 댓글 비추
//    @PutMapping("/rest/down")
//    public void down(Reply reply){
//        boardService.down(reply);
//    }
//
//    //메일 전송
//    @PostMapping("/rest/email")
//    public void sendEmail(Email email) throws Exception{
//        emailService.sendSimpleMessage(email.getUserEmail());
//    }
//
//    // 인증 코드 확인
//    @PostMapping("/rest/confirm")
//    public int confirm(Email email) {
//        if(com.cuteblog.bbs4.service.EmailService.ePw.equals(email.getConfirm())){
//            return com.cuteblog.bbs4.service.EmailService.CONFIRM;
//        }else{
//            return com.cuteblog.bbs4.service.EmailService.REJECT;
//        }
//
//    }
//
//    // 유저삭제
//    @GetMapping("/rest/memberDelete/{mno}")
//    public void memberDelete(@PathVariable long mno, HttpServletResponse response) throws IOException {
////        System.out.println(mno);
//        memberService.memberDelete(mno);
//        response.sendRedirect("/memberList");
//    }
//
////    // 댓글 삭제
////    @GetMapping("/rest/replyDelete/{rno}")
////    public String replyDelete(@PathVariable long rno){
//////        System.out.println(mno);
////        memberService.memberDelete(rno);
////        return "redirect:/memberList";
////    }
//
//}
