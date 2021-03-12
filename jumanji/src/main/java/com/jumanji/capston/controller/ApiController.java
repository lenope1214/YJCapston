package com.jumanji.capston.controller;

import com.jumanji.capston.service.UserService;
import com.jumanji.capston.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class ApiController {
    @Autowired
    UserService userService;



    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody User user) {
        return new ResponseEntity<>(userService.insert(user), HttpStatus.CREATED);
    }



    @GetMapping("/userDelAll")
    public ResponseEntity<?> memberDelAll(){
        return new ResponseEntity<>(userService.deleteAll(), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/myInfo/{id}")
    public ResponseEntity<?> myInfo(@PathVariable("id") String id){
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/userList")
    public ResponseEntity<?> Members() {
        List<User> memberList;
        memberList = userService.findAll();
        return new ResponseEntity<>(memberList, HttpStatus.OK);
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
}
