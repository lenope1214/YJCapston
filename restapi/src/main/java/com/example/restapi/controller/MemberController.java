package com.example.restapi.controller;

import com.example.restapi.model.Member;
import com.example.restapi.model.Shop;
import com.example.restapi.repository.MemberRepository;
import com.example.restapi.repository.ShopRepository;
import com.example.restapi.service.MemberService;
import com.example.restapi.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/member")
@RestController
@RequiredArgsConstructor
@Log4j2
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ShopRepository shopRepository;

    private ShopService shopService;

    private MemberService memberService;

//    @GetMapping("/test")
//    public Member memberTest(){
//        return new Member(0L, "Wickies", 20, "제주", new Date());
//    }
//
//    @GetMapping("/test2")
//    public ArrayList<Member> memberTest2(){
//        return new ArrayList(Arrays.asList(
//                new Member(1L, "Wickies", 20, "제주", new Date()),
//                new Member(2L, "마동석", 40, "전주", new Date()),
//                new Member(3L, "조승우", 22, "무주", new Date()),
//                new Member(4L, "박보영", 32, "진주", new Date()),
//                new Member(5L, "엄복동", 17, "상주", new Date())
//        ));
//    }

    @PostMapping("/insert") // CREATE
    public Member insert(@RequestBody Map<String,String> map){

       return memberRepository.save(new Member(map.get("id"),
                                            map.get("pw"),
                                            map.get("name"),
                                            "",
                                            "",
                                            null,
                                            map.get("phone"),
                                            'N',
                                            map.get("role"),
                                            "jm",
                                            null,
                                            "0",
                                            0,
                                            200));
//                .id(map.get("id"))
//                .pw(map.get("pw"))
//                .name(map.get("name"))
//                .email("")
//                .address("")
//                .birthday(null)
//                .phone(map.get("phone"))
//                .is_wdrw('N')
//                .role(map.get("role"))
//                .social("jm")
//                .sign_date(null)
//                .level("0")
//                .point(0)
//                .code(200)
//        return "회원가입 완료";
//        return memberRepository.save(new Member(map.get("id"),
//                                        map.get("pw"),
//                                        map.get("name"),
//                                        map.get("phone"),
//                                        map.get("role")));
    }

    @PostMapping("/registerShop")
    public String registerShopOne(@RequestBody Map<String, String> map) {
        System.out.println("controller@@@@:" + map);

        Member member = Member.builder().id(map.get("member_id")).build();

        Shop shop = shopRepository.save(new Shop(map.get("id"),
                                        map.get("name"),
                                        map.get("intro"),
                                        Integer.parseInt(map.get("open")),
                                        Integer.parseInt(map.get("close")),
                                        map.get("address"),
                                        map.get("category"),
                                        map.get("is_re_pos").charAt(0),
                                        member));
        System.out.println(shop);

        return "성공";
        //shopService.register(map);

        //Member member = memberRepository.findByIdd(member_id);

        //System.out.println("2@@@@@@@@@@@@@@@@@@@@@@" + member);

//        return shopRepository.save(new Shop(map.get("id"),
//                map.get("name"),
//                map.get("intro"),
//                Integer.parseInt(map.get("open")),
//                Integer.parseInt(map.get("close")),
//                map.get("address"),
//                map.get("category"),
//                map.get("is_re_pos").charAt(0))
//        );
    }

//    @GetMapping("/select") // READ
//    public List<Member> selectAll(){
//        return memberRepository.findAll();
//    }
//
//    @GetMapping("/select/{id}") // READ
//    public Member selectOne(@PathVariable("id") long id){
//        return memberRepository.findById(id).orElse(null);
//    }
//
//    @PostMapping("/update/{id}") // UPDATE
//    public Member updateOne(@PathVariable("id") long id, @RequestBody Map<String, String> map){
//        System.out.println(id);
//        System.out.println(map);
//        Member member = memberRepository.findById(id).orElse(null);
//        member.setId(map.get("id"));
//        member.setPw(map.get("pw"));
//        return memberRepository.save(member);
//    }

//    @PostMapping("/delete/{id}") // DELETE
//    public String deleteOne(@PathVariable("id") long id){
//        memberRepository.deleteById(id);
//        return "삭제 완료";
//    }

    @PostMapping("/validate/{id}") // valudate
    public String validateOne(@PathParam("id") String id) {
        if(memberRepository.findByMemberId(id) == null) {
            return "중복아님";
        }
        return null;
    }

    @PostMapping("/login") // login
    public Member login(@PathParam("id") String id,
                          @PathParam("pw") String pw) {
        Member member = memberRepository.findLogin(id, pw);
        if(member == null) {
            return new Member("d","d","d","d","d",null,"d",'o',"d","d",null,"d",0,400);
        } else {
            return member;
        }
    }

    int intParser(String age){
        try{
            return Integer.parseInt(age);
        } catch(ClassCastException e){
            e.printStackTrace();
            return 0;
        }
    }

    @PostMapping("findshop/{id}") // 가게여부확인
    public String findshopOne(@PathParam("id") String id) {
        if(memberRepository.findByShop(id) == null) {
            return "가게없음";
        }
        return "가게있음";
    }



}
