package com.jumanji.capston.controller.externalApiController;

import com.jumanji.capston.data.Payment;
import com.jumanji.capston.data.externalData.iamport.Iamport;
import com.jumanji.capston.service.external.IamportClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/iamport")
public class IamportController {
    @Autowired
    private IamportClientService service;

    @Transactional
    @PostMapping("/get-token")
    public ResponseEntity<?> usersGetToken(@RequestHeader String authorization) throws Exception {
        return new ResponseEntity<>(service.getToken(authorization), HttpStatus.OK);
    }

    @org.springframework.transaction.annotation.Transactional
    @PostMapping("/cancel")
    public ResponseEntity<?> payCanceled(@RequestHeader String authorization, @RequestBody Iamport.CancelData cancelData) throws Exception {
        return new ResponseEntity<>(service.cancelPayment(authorization, cancelData), HttpStatus.OK);
    }

}
