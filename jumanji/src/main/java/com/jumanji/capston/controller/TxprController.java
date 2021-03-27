package com.jumanji.capston.controller;

import com.jumanji.capston.data.Request.TxprDscNoRequest;
import com.jumanji.capston.service.TxprService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1") // 국세청 관련 api
public class TxprController {
    @Autowired
    TxprService txprService;
    @Autowired
    HttpHeaders httpHeaders;

    @GetMapping("/validateDscNo")
    public ResponseEntity<?> getTxprDscNoRes(@RequestBody TxprDscNoRequest req){
        String result = "";
        result = txprService.getTaxTypeFromNts(req.getId());
        if(result != null) return new ResponseEntity<>(result, httpHeaders, HttpStatus.OK);
        else return new ResponseEntity<>("사업자 번호가 잘못된듯?", httpHeaders, HttpStatus.BAD_REQUEST);
    }
}
