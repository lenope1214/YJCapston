package com.jumanji.capston.controller;

import com.jumanji.capston.config.jwt.JwtResponse;
import com.jumanji.capston.config.jwt.JwtTokenUtil;
import com.jumanji.capston.controller.exception.ApiErrorResponse;
import com.jumanji.capston.controller.exception.UserException.UserNotFoundException;
import com.jumanji.capston.data.Request.UserDto;
import com.jumanji.capston.data.User;
import com.jumanji.capston.service.ShopService;
import com.jumanji.capston.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ApiController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserService userService;
    @Autowired
    ShopService shopService;
    @Autowired
    HttpHeaders httpHeaders;


//    @Autowired
//    private PrincipalDetailsService userDetailService;

    @Transactional // 트랜잭션화 시켜서 오류발생시 롤백이 되도록
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody UserDto user) {
        System.out.println("회원가입 요청 ");
        System.out.println(user.toString());
        User userEntity = userService.insert(user);
        if (userEntity == null) return new ResponseEntity<>("회원가입 실패", httpHeaders, HttpStatus.BAD_REQUEST);
        else {
            System.out.println("가입일자 : " + userEntity.getSignDate());
            return new ResponseEntity<>(userEntity.getId(), HttpStatus.CREATED);
        }
    }

    @Transactional(readOnly = true) // 트랜잭션이긴 한데 읽기 전용으로 속도 업 !
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User _user) throws Exception {
//        return new ResponseEntity<>(userService.findById(_user.getId()), HttpStatus.BAD_REQUEST);
        System.out.println("/api/v1/login 요청");
        User user = userService.findById(_user.getId());

//         아이디 오류 후에 아이디, 비번 오류 통합.. 현재는 있는지 확인하기 위해 이렇게 둠.
        if (user == null) return new ResponseEntity<>(new ApiErrorResponse("error-0001", "Not Found User id : " + _user.getId()), HttpStatus.BAD_REQUEST);

        if (userService.checkPW(_user, user.getPassword())) {
            final String access_token = jwtTokenUtil.generateToken(user.getId());
            JwtResponse jwtResponse = new JwtResponse(access_token, user.getRole());
            return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
        } else { // 비밀번호 오류
            return new ResponseEntity<>(new ApiErrorResponse("error-0002", "missmatch password"), HttpStatus.BAD_REQUEST);
//            return new ResponseEntity<>("로그인 실패",  httpHeaders,HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/validate/{id}") // validate
    public ResponseEntity<?> validateOne(@PathVariable String id) {
        try{
            userService.findById(id);
            return new ResponseEntity<>("있는 ID", httpHeaders, HttpStatus.BAD_REQUEST);
        }catch (UserNotFoundException e){
            return new ResponseEntity<>("없는 ID", httpHeaders, HttpStatus.OK);
        }
    }
}




