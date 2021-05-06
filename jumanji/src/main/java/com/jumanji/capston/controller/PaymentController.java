package com.jumanji.capston.controller;

import com.jumanji.capston.data.Payment;
import com.jumanji.capston.data.externalData.iamport.Iamport;
import com.jumanji.capston.service.OrderServiceImpl;
import com.jumanji.capston.service.PaymentServiceImpl;
import com.jumanji.capston.service.external.IamportClientService;
import com.jumanji.capston.service.external.iamportAndroid.response.IamportResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.Query;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.math.BigDecimal;
import java.sql.Timestamp;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class PaymentController {

    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private PaymentServiceImpl paymentService;
    @Autowired
    IamportClientService iamportClientService;

    @Transactional
    @GetMapping("/payment/{orderId}")
    public ResponseEntity<?> getPayment(@RequestHeader String authorization, @PathVariable Timestamp orderId){
        Payment payment = paymentService.get(authorization, orderId);
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/payment/complite")
    public ResponseEntity<?> complePayment(@RequestParam("imp_uid") String impUid, @RequestParam("merchant_uid") String merchantUid, HttpServletRequest request) throws Exception {
        System.out.println("request info" +
                "request.getQueryString" + request.getQueryString()+"\n" +
                "merchantUid.substring(merchant_.length()) : " + merchantUid.substring("merchant_".length()));
        IamportResponse<Iamport.Payment> response = null;
        if(merchantUid == null)throw new Exception("merchantUid is null");
        try {
//            String m_id = merchantUid;
            response = iamportClientService.paymentByMerchantUid(merchantUid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(response.getResponse().getStatus().equals("paid")){
            String mId = merchantUid.substring("merchant_".length());
            System.out.println("mid : " + mId);
            int amount = orderService.getOrderInfo(new Timestamp(Long.parseLong(mId))).getAmount();
            BigDecimal amountB = BigDecimal.valueOf(amount);
            System.out.println("response.getResponse().getAmount() : " + response.getResponse().getAmount());
            System.out.println("BigDecimal amount : " + amountB);
            if(response.getResponse().getAmount() == amountB){
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Transactional
    @PostMapping("/payment")
    public ResponseEntity<?> postPayment(@RequestHeader String authorization, @RequestBody Payment.Request request) {
        // response 형태로 바꿔줘야함.
        return new ResponseEntity(paymentService.post(authorization, request), HttpStatus.CREATED);
    }



}
