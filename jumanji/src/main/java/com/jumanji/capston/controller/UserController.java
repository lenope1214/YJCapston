package com.jumanji.capston.controller;

import com.jumanji.capston.Payload.Request.UserRequest;
import com.jumanji.capston.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    com.jumanji.capston.service.UserService userService;

    @Transactional(readOnly = true)
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserInfo(@PathVariable String id) throws Exception {
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

    @GetMapping("/user")
    public ResponseEntity<?> getMyInfo() {
        System.out.println("APIcon /user 진입.");
        System.out.println("로긘 유저 id : " + SecurityContextHolder.getContext().getAuthentication().getName());

        User userEntity = userService.findById(SecurityContextHolder.getContext().getAuthentication().getName());

        if (userEntity == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userEntity, HttpStatus.OK);


//        return new ResponseEntity<>("is not match login id <-> request id.", HttpStatus.FORBIDDEN);
    }


    @Transactional
    @DeleteMapping("/userDelAll")
    public ResponseEntity<?> userDelAll() {
        return new ResponseEntity<>(userService.deleteAll(), HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/user") // putUser
    public ResponseEntity<?> updateUser(@RequestBody UserRequest putUserDTO) {
        return new ResponseEntity<>(userService.updateUser(putUserDTO), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/userList") // getUserList
    public ResponseEntity<?> getUserList() {
        List<User> userList;
        userList = userService.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}
