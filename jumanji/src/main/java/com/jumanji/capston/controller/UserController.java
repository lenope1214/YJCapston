package com.jumanji.capston.controller;

import com.jumanji.capston.DTO.PutUserDTO;
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
public class UserController {
    @Autowired
    com.jumanji.capston.service.UserService userService;

    @Transactional(readOnly = true)
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserInfo(@PathVariable String id) throws Exception {
        System.out.println("APIcon user/{id} 진입.");
        final User userEntity = userService.findById(id);
        System.out.println("로긘 유저 id : " + SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println("찾을 유저 id : " + id + "\ngetUser : " + userEntity.getId());

        if (SecurityContextHolder.getContext().getAuthentication().getName().equals(userEntity.getId())) {
            if (userEntity == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
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
    @PutMapping("/user")
    public ResponseEntity<?> putUser(@RequestBody PutUserDTO putUserDTO){
        User user = userService.put(putUserDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/userList")
    public ResponseEntity<?> getUserList() {
        List<User> userList;
        userList = userService.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}
