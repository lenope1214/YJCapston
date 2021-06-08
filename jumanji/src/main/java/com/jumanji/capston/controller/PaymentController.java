package com.jumanji.capston.controller;

import com.jumanji.capston.data.Order;
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
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/")
public class PaymentController {

    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private PaymentServiceImpl paymentService;
    @Autowired
    IamportClientService iamportClientService;

    @Transactional
    @GetMapping("payments/{orderId}")
    public ResponseEntity<?> getPayment(@RequestHeader String authorization, @PathVariable Timestamp orderId){
        Payment payment = paymentService.get(authorization, orderId);
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    @Transactional
    @GetMapping("payments/complite")
    public ResponseEntity<?> complePayment(@RequestParam("imp_uid") String impUid, @RequestParam("merchant_uid") String merchantUid, HttpServletRequest request) throws Exception {
        System.out.println("request info" +
                "request.getQueryString" + request.getQueryString()+"\n" +
                "merchantUid.substring(merchant_.length()) : " + merchantUid.substring("merchant_".length()));
        Iamport.IamportResponse<Iamport.Payment> response = null;
        String mId = merchantUid;
        if(merchantUid.contains("_")){
            mId = mId.substring(mId.indexOf('_')+1);
        }
        System.out.println("mid : " + mId);
        if(merchantUid == null)throw new Exception("merchantUid is null");
        try {
//            String m_id = merchantUid;
            response = iamportClientService.paymentByMerchantUid(merchantUid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(response.getResponse().getStatus().equals("paid")){
            System.out.println("요청 후mid : " + mId);
            int amount = orderService.isPresent(new Timestamp(Long.parseLong(mId))).getAmount();
            BigDecimal amountB = BigDecimal.valueOf(amount);
            System.out.println("response.getResponse().getAmount() : " + response.getResponse().getAmount());
            System.out.println("BigDecimal amount : " + amountB);
            if(response.getResponse().getAmount() == amountB){
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

//    @Transactional
    @PostMapping("payments")
    public ResponseEntity<?> postPayment(@RequestHeader String authorization, @RequestBody Payment.Request request) {
        // response 형태로 바꿔줘야함.
        Payment payment = paymentService.post(authorization, request);
        Payment.Response response = new Payment.Response(payment);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    /**
     *
     * @param shopId 요청 식당 번호
     * @param scope 요청 기준, 일(day), 주(week), 월(month)
     * @param date  요청 일자 yyyyMMdd
     * @return
     */
    @Transactional
    @GetMapping("shops/{shopId}/payments/statistics")
    public ResponseEntity<?> getStatistics(@RequestHeader String authorization, @PathVariable String shopId, @RequestParam String scope, @RequestParam String date){
        Payment.StatisticsDAO statistics = paymentService.getShopStatistics(authorization, shopId, scope, date);

        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }

}
