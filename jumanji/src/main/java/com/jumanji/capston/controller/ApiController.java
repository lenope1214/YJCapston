package com.jumanji.capston.controller;

import com.jumanji.capston.config.jwt.JwtTokenUtil;
import com.jumanji.capston.data.User;
import com.jumanji.capston.service.ShopServiceImpl;
import com.jumanji.capston.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class ApiController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ShopServiceImpl shopService;
    @Autowired
    HttpHeaders httpHeaders;


//    @Autowired
//    private PrincipalDetailsService userDetailService;

    @Transactional // 오류발생시 롤백이 되도록
    @PostMapping("join")
    public ResponseEntity<?> join(@RequestBody User.Request request) {
        System.out.println("회원가입 요청 ");
        User user = userService.post(request);
        User.Response response = new User.Response(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Transactional(readOnly = true)
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody User.Request user) {
        System.out.println("/api/v1/login 요청");
        return userService.login(user); // 로그인은 이렇게 두자.
    }


    @GetMapping("validate/{id}") // validate
    public ResponseEntity<?> validateOne(@PathVariable String id) {
        if(userService.isEmpty(id))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("users/{userId}/device-token")
    public ResponseEntity<?> getDeviceToken(@PathVariable String userId){
        String deviceToken = userService.getDeviceToken(userId);
        return new ResponseEntity<>(deviceToken, HttpStatus.OK);
    }



//    @GetMapping("shop/{shopId}/device-token")
//    public ResponseEntity<?> getDeviceTokenBy

}
