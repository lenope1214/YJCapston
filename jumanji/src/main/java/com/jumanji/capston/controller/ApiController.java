package com.jumanji.capston.controller;

import com.jumanji.capston.Service.UserSerivce;
import com.jumanji.capston.controller.exception.MemberNotFoundException;
import com.jumanji.capston.data.User;
import com.jumanji.capston.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class ApiController {
    @Autowired
    UserSerivce userSerivce;



    @PostMapping("/join")
    public String join(@RequestBody User user) {
        userSerivce.insert(user);
        return "/";
    }

    //spring security 설정 .loginProcessingUrl("/login") 으로 처리
//    @Transactional(readOnly = true)
//    @PostMapping("/login")
//    public String login(@ModelAttribute User user) {
//        System.out.println(">> login");
//        System.out.println("m.toString() : " + user.toString() + "\n"
//                + "m.getId() : " + user.getId() + "\n"
//                + "m.getPw() : " + user.getPw() + "\n"
////              + "m.getName() : " + user.getName() + "\n"
////              + "m.getPhone() : " + user.getPhone() + "\n"
////              + "m.getRole() : " + user.getRole()
//        );
////        return userRepository.findById(user.getId())
////                .orElseThrow(()-> new MemberNotFoundException(user.getId()));
//        return "login";
//    }

    @GetMapping("/userDelAll")
    public ResponseEntity<?> memberDelAll(){
        return new ResponseEntity<>(userSerivce.deleteAll(), HttpStatus.OK);
    }

//    @Transactional(readOnly = true)
    @GetMapping("/myInfo/{id}")
    public User myInfo(@PathVariable("i4d") String id){
        return userRepository.findById(id)
                .orElseThrow(()-> new MemberNotFoundException(id));
    }

//    @Transactional(readOnly = true)
    @GetMapping("/userList")
    public List<User> Members() {
        List<User> memberList;
        memberList = userRepository.findAll();
        return memberList;
    }

    @PostMapping("token")
    public String token(){
        return "token";
    }
}
