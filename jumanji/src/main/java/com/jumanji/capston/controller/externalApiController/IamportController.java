package com.jumanji.capston.controller.externalApiController;

import com.jumanji.capston.service.External.IamportClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/iamport")
public class IamportController {
    @Autowired
    private IamportClientService service;

    @Transactional
    @PostMapping("/users/getToken")
    public ResponseEntity<?> usersGetToken() throws Exception {
        return new ResponseEntity<>(service.getToken(), HttpStatus.OK);
    }


}
