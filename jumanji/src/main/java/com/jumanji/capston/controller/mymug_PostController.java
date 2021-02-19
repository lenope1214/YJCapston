//package com.codesample.mymug.controller;
//
//import com.codesample.mymug.data.Board;
//import com.codesample.mymug.data.Reply;
//import com.codesample.mymug.repository.BoardRepository;
//import com.codesample.mymug.repository.ReplyRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Date;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/post")
//public class PostController {
//    @Autowired
//    BoardRepository boardRepository;
//    @Autowired
//    ReplyRepository replyRepository;
//
//    @PostMapping("/addBoard")
//    public Board addBoard(@RequestBody Board board ,
//            Model model){
//        System.out.println("board.toString() : " + board.toString());
//        Board b = new Board();
//
//        b.setTitle(board.getTitle());
//
//        b.setRegDate(new Date());
//        b.setPassword(board.getPassword());
//        b.setContent(board.getContent());
//        boardRepository.save(b);
//        System.out.println(
//                "제목 : " + b.getTitle() + "\n" +
//                "비번 : " + b.getPassword() + "\n" +
//                "내용 : " + b.getContent() + "\n");
//        return b;
//    }
//
//    @PostMapping("/reply/{pno}") // /post/reply/{pno}
//    public Reply addComment(@RequestBody Reply reply, @PathVariable long pno, Authentication auth,
//                            Model model){
//        Reply r = new Reply();
//        System.out.println("addComment 진입");
//        System.out.println("auth : " + auth);
//        r.setRWriter(auth.getName());
//        r.setComment(reply.getComment());
//        r.setPno(boardRepository.findByPno(pno));
//        r.setRegDate(new Date());
//        System.out.println("comment : " + r.getComment());
//        replyRepository.save(r);
//        return r;
//    }
//
//    @PutMapping("reply/up")
//    public void replyUp(@RequestBody Map<String, Long> param){
//        long rno = param.get("rno");
////        long pno = param.get("pno");
//        Reply r = replyRepository.findByRno(rno);
//        r.setUp(r.getUp()+1);
//        replyRepository.save(r);
//    }
//
//    @PutMapping("reply/down")
//    public void replyDown(@RequestBody Map<String, Long> param){
//        long rno = param.get("rno");
////        long pno = param.get("pno");
//        Reply r = replyRepository.findByRno(rno);
//        r.setDown(r.getDown()+1);
//        replyRepository.save(r);
//    }
//
////    @PostMapping("/update")
////    public String updateResult(@RequestParam(name="userid")String userid,
////                               @RequestParam(name="point")int point, Model model){
////        model.addAttribute("result", userid + " " + point);
////        return "updatePoint";
////    }
//
//
//
//}