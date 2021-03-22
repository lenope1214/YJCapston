//package com.jumanji.capston.service;
//
//import com.jumanji.capston.data.Payment;
//import com.jumanji.capston.data.Refund;
//import com.jumanji.capston.repository.PaymentRepository;
//import com.jumanji.capston.repository.RefundRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class RefundService {
//    @Autowired
//    RefundRepository refundRepository;
//    @Autowired
//    PaymentRepository paymentRepository;
//
//    public Refund findById(Long id){
//        Payment payment = paymentRepository.findById(id)
//                .orElseThrow(()-> new IllegalArgumentException("id를 확인해주세요!!!"));;
//        return refundRepository.findById(payment)
//                .orElseThrow(()-> new IllegalArgumentException("id를 확인해주세요!!!"));
//    }
//
//
//    public Refund insert(Refund _refund ){
//        return refundRepository.save(_refund);
//    }
//
//    public String delete(Refund _refund){
//        Refund refund  = refundRepository.findById(_refund.getId()).orElseThrow(()-> new IllegalArgumentException("id를 확인해주세요!!!"));
//        refundRepository.delete(refund);
//        return "ok";
//    }
//
//    public List<Refund> findAll() {
//        return refundRepository.findAll();
//    }
//}
