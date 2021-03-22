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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

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
        if (userEntity == null) return new ResponseEntity<>(userEntity, HttpStatus.CREATED);
        return new ResponseEntity<>(userEntity, HttpStatus.BAD_REQUEST);
        // 얘는 좀 더 세부화 시켜서 리턴해줍시다...!!! 는 너무 어렵고~
    }

    @Transactional(readOnly = true) // 트랜잭션이긴 한데 읽기 전용으로 속도 업 !
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User _user) throws Exception {
        System.out.println("/api/v1/login 요청");
        final User user = userService.findById(_user.getId());
        if (userService.checkPW(_user, user.getPassword())) {
            System.out.println("비밀번호 체크 성공!");
            final String access_token = jwtTokenUtil.generateToken(user.getId());
            return new ResponseEntity<>(new JwtResponse(access_token), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/validate/{id}") // valudate
    public String validateOne(@PathParam("id") String id) {
        if(userService.findById(id) == null) {
            return "중복아님";
        }
        return null;
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




