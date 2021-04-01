package com.jumanji.capston.controller;

import com.jumanji.capston.service.TxprService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1") // 국세청 관련 api
public class TxprController {
    @Autowired
    TxprService txprService;
    @Autowired
    HttpHeaders httpHeaders;

    @GetMapping("/validateDscNo")
    public ResponseEntity<?> getTxprDscNoRes(@RequestParam String id){
        String result = "";
        System.out.println("사업자 인증 요청 : " + id);
        result = txprService.getTaxTypeFromNts(id);
        if(result != null) return new ResponseEntity<>(result, httpHeaders, HttpStatus.OK);
        else return new ResponseEntity<>("사업자 번호가 잘못된듯?", httpHeaders, HttpStatus.BAD_REQUEST);
    }
}
