//package com.codesample.mymug.controller;
//
//
//import com.codesample.mymug.repository.BoardRepository;
//import com.codesample.mymug.repository.MenuRepository;
//import com.codesample.mymug.repository.ReplyRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.security.Principal;
//import java.util.List;
//import java.util.Optional;
//
//@Controller
//public class WebController {
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    MenuRepository menuRepository;
//    @Autowired
//    BoardRepository boardRepository;
//    @Autowired
//    ReplyRepository replyRepository;
//    @Autowired
//    CrewMapper crewMapper;
//
//
//    @GetMapping(value={"/","/home"})
//    public String home(Model model) {
////        model.addAttribute("message", "안녕하세요 My Mug 입니다.");
////        model.addAttribute("img1", "image/drip.jpg");
////        model.addAttribute("img2", "image/espresso.jpg");
//        model.addAttribute("posts", boardRepository.findAll());
////        model.addAttribute("cntReply", replyRepository.findCountByPno((List<Board>) model.getAttribute("posts")));
//        return "home";
//    }
//
//    @GetMapping("/select/{pno}")
//    public String selectBoard(@PathVariable("pno") long pno, Model model){
//            System.out.println("PNO : " + pno);
//            Board b = boardRepository.findByPno(pno);
//            b.setViews(b.getViews()+1);
//            boardRepository.save(b);
//            model.addAttribute("board", b);
//            List<Reply> rList = replyRepository.findByPno_Pno(pno);
//            if(rList.size() > 0) model.addAttribute("reply", rList);
//        return "board"; // board 페이지로 이동
//    }
//
//    @GetMapping("/menus")
//    public String menus(@RequestParam(required=false) String name, Model model) {
//        if(name==null || name.isEmpty())
//            model.addAttribute("menus", menuRepository.findAll());
//        else
//            model.addAttribute("menus", menuRepository.findByNameContains(name));
//        return "menus";
//    }
//
//
//
//    @GetMapping("/update")
//    public String updateView(){
//        return "updatePoint";
//    }
//
//    @PostMapping("/update")
//    public String updateResult(@RequestParam(name="userid")String userid,
//                               @RequestParam(name="point")int point, Model model){
//            model.addAttribute("result", userid + " " + point);
//            return "updatePoint";
//    }
//
//    @GetMapping("/mypoint")
//    public String points(Principal p) {
//        if(p!=null)
//            return "redirect:mypoint/"+p.getName();
//        else
//            return "redirect:home";
//    }
//
//    @GetMapping("/mypoint/{userid}")
//    public String myPoint(@PathVariable("userid") String userid, Model model) {
//        System.out.print("webcon/mypoint/userid : " + userid);
//
//        Optional<User> user = userRepository.findById(userid);
//        if (user.isPresent()) {
//            Point p = new Point(userid, user.get().getPoint());
//            System.out.print("webcon/mypoint/userid - point : ");
//            System.out.println(p.getUserid());
//            model.addAttribute("point", p);
//        }
//        return "myPoint";
//    }
//
//    @GetMapping("/adduser")
//    public String user() {
//        return "addUser";
//    }
//
//
//    @GetMapping("/crews")
//    public String crews(Model model, Authentication auth) {
//        if(auth!=null) System.out.println(auth.getName());
//        model.addAttribute("crews", crewMapper.findAll());
//        return "crews";
//    }
//
//    @GetMapping("/test")
//    public String test(Model model) {
//        model.addAttribute("username", "이성복");
//        return "test";
//    }
//
//
//
//    @GetMapping("/post")
//    public String insertPost(){
//        return "addBoard";
//    }
//
////    @GetMapping("/post/{pno}")
////    public String selectPost(@PathVariable("pno") long pno, Model model){
////        model.addAttribute("postInfo", boardRepository.findByPno(pno));
////        boardRepository.findByPno(pno);
////        return "board";
////    }
//
//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
//    @GetMapping("/denied")
//    public String denied() {
//        return "denied";
//    }
//}
