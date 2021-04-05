package com.jumanji.capston.controller;

import com.jumanji.capston.config.jwt.JwtTokenUtil;
import com.jumanji.capston.controller.commons.Controller;
import com.jumanji.capston.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class UserController extends Controller {
    @Autowired
    com.jumanji.capston.service.UserService userService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Transactional(readOnly = true)
    @GetMapping("/user")
    public ResponseEntity<?> selectMyInfo(@RequestHeader String authorization) {
        System.out.println("APIcon /user 진입.");
        String loginId = jwtTokenUtil.getUsername(authorization);
        System.out.println("로긘 유저 id : " + loginId);

        User userEntity = userService.findById(loginId);

        if (userEntity == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userEntity, HttpStatus.OK);
//        return new ResponseEntity<>("is not match login id <-> request id.", HttpStatus.FORBIDDEN);
    }

    @Transactional(readOnly = true)
    @GetMapping("/user/{id}")
    public ResponseEntity<?> selectUserInfo(@PathVariable String id) throws Exception {
        System.out.println("APIcon user/{id} 진입.");
        if(userService.findById(id) == null)return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        final User userEntity = userService.findById(id);
        System.out.println("로긘 유저 id : " + SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println("찾을 유저 id : " + id + "\ngetUser : " + userEntity.getId());

        if (SecurityContextHolder.getContext().getAuthentication().getName().equals(userEntity.getId())) {
            return new ResponseEntity<>(userEntity, HttpStatus.OK);
        }


        return new ResponseEntity<>("is not match login id <-> request id.", HttpStatus.FORBIDDEN);
    }




    @Transactional
    @DeleteMapping("/userDelAll")
    public ResponseEntity<?> userDelAll() {
        return new ResponseEntity<>(userService.deleteAll(), HttpStatus.OK);
    }

    @Transactional
    @PatchMapping("/user") // putUser
    public ResponseEntity<?> updateUser(@RequestBody User.Request request, @RequestHeader String authorization) {
        String loginId = jwtTokenUtil.getUsername(authorization);
        User.Response response = new User.Response(userService.findById(loginId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/userList") // getUserList
    public ResponseEntity<?> getUserList() {
        List<User> userList;
        userList = userService.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}