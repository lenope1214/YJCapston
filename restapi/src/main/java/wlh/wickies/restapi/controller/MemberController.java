package wlh.wickies.restapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wlh.wickies.restapi.model.Member;
import wlh.wickies.restapi.repository.MemberRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping("/member")
@RestController
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

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
        return memberRepository.save(new Member(map.get("userid"), map.get("userpw"), map.get("name"), map.get("phone"), map.get("role")));
    }

    @GetMapping("/select") // READ
    public List<Member> selectAll(){
        return memberRepository.findAll();
    }

    @GetMapping("/select/{id}") // READ
    public Member selectOne(@PathVariable("id") long id){
        return memberRepository.findById(id).orElse(null);
    }

    @PostMapping("/update/{id}") // UPDATE
    public Member updateOne(@PathVariable("id") long id, @RequestBody Map<String, String> map){
        System.out.println(id);
        System.out.println(map);
        Member member = memberRepository.findById(id).orElse(null);
        member.setUserid(map.get("userid"));
        member.setUserpw(map.get("userpw"));
        return memberRepository.save(member);
    }

    @PostMapping("/delete/{id}") // DELETE
    public String deleteOne(@PathVariable("id") long id){
        memberRepository.deleteById(id);
        return "삭제 완료";
    }

    int intParser(String age){
        try{
            return Integer.parseInt(age);
        } catch(ClassCastException e){
            e.printStackTrace();
            return 0;
        }
    }
}
