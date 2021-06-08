package com.jumanji.capston.controller.external;

import com.jumanji.capston.service.external.IamportClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/iamport/")
public class IamportController {
    @Autowired
    private IamportClientService service;

    @Transactional
    @PostMapping("get-token")
    public ResponseEntity<?> usersGetToken(@RequestHeader String authorization) throws Exception {
        return new ResponseEntity<>(service.getToken(authorization), HttpStatus.OK);
    }

    @Transactional
    @GetMapping("cancel")
    public ResponseEntity<?> payCancel(@RequestHeader String authorization, @RequestParam("m_id") String m_id) throws Exception {
        return new ResponseEntity<>(service.cancelPayment(authorization, m_id), HttpStatus.OK);
    }

    @Transactional
    @GetMapping("shop/cancel")
    public ResponseEntity<?> payShopCancel(@RequestHeader String authorization, @RequestParam("m_id") String m_id, @RequestParam("shop_id") String shopId) throws Exception {
        return new ResponseEntity<>(service.cancelPaymentByShop(authorization, m_id, shopId ), HttpStatus.OK);
    }

}