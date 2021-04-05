package com.jumanji.capston.controller.commons;

import com.jumanji.capston.config.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class Controller {
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    protected String getLoginUserId(String authorization){
        return jwtTokenUtil.getUsername(authorization);
    }
}