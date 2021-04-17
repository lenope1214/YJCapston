package com.jumanji.capston.controller;

import com.jumanji.capston.data.User;
import com.jumanji.capston.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class UserController  {
    @Autowired
    UserServiceImpl userService;


    @Transactional(readOnly = true)
    @GetMapping("/user")  // myInfo
    public ResponseEntity<?> selectMyInfo(@RequestHeader String authorization) {
        return userService.findById(userService.getMyId(authorization));
//        return new ResponseEntity<>("is not match login id <-> request id.", HttpStatus.FORBIDDEN);
    }


    @Transactional(readOnly = true)
    @GetMapping("/userList") // getUserList
    public ResponseEntity<?> getUserList(@RequestHeader String authorization) {
        return userService.getList(authorization);
    }

    @Transactional(readOnly = true)
    @GetMapping("/user/{id}")
    public ResponseEntity<?> selectUserInfo(@PathVariable String id) {
        return userService.findById(id);
    }

//    @Transactional
//    @DeleteMapping("/userDelAll")
//    public ResponseEntity<?> userDelAll( @RequestHeader String authorization) {
//        return userService.deleteAll(authorization);
//    }

    @Transactional
    @PatchMapping("/user") // patch user
    public ResponseEntity<?> patchUser(@RequestBody User.Request request, @RequestHeader String authorization) {
        return userService.patch(authorization, request);
    }

    @Transactional
    @DeleteMapping("/user") // delete user
    public ResponseEntity<?> deleteUser(@RequestHeader String authorization){
        return userService.delete(authorization);
    }
}