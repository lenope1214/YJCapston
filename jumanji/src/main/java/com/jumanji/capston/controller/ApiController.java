package com.jumanji.capston.controller;

import com.jumanji.capston.config.jwt.JwtResponse;
import com.jumanji.capston.config.jwt.JwtTokenUtil;
import com.jumanji.capston.data.User;
import com.jumanji.capston.service.ShopService;
import com.jumanji.capston.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class ApiController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserService userService;
    @Autowired
    ShopService shopService;


//    @Autowired
//    private PrincipalDetailsService userDetailService;

    @Transactional // 트랜잭션화 시켜서 오류발생시 롤백이 되도록
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody User user) {
        User userEntity = userService.insert(user);
        if (userEntity == null) return new ResponseEntity<>("회원가입 실패", HttpStatus.BAD_REQUEST);
        else {
            System.out.println("가입일자 : " + userEntity.getSignDate());
            return new ResponseEntity<>(userEntity, HttpStatus.CREATED);
        }
        // 얘는 좀 더 세부화 시켜서 리턴해줍시다...!!! 는 너무 어렵고~
    }

    @Transactional(readOnly = true) // 트랜잭션이긴 한데 읽기 전용으로 속도 업 !
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User _user) throws Exception {
        System.out.println("/api/v1/login 요청");
        final User user = userService.findById(_user.getId());
        if (user == null) return new ResponseEntity<>("없는 유저 입니다.", HttpStatus.BAD_REQUEST);
        if (userService.checkPW(_user, user.getPassword())) {
            final String access_token = jwtTokenUtil.generateToken(user.getId());
            JwtResponse jwtResponse = new JwtResponse(access_token, user.getRole());
            return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("로그인 실패", HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/validate/{id}") // validate
    public ResponseEntity<?> validateOne(@PathVariable String id) {
        if (userService.findById(id) != null) {
            return new ResponseEntity<>("있는 ID", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("없는 ID", HttpStatus.OK);
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




