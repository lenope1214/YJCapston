package com.jumanji.capston.controller.externalApiController;

import com.jumanji.capston.service.external.TxprService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping("/validateDscNo")
    public ResponseEntity<?> getTxprDscNoRes(@RequestParam String id){
        return txprService.getTaxTypeFromNts(id);
    }
}
