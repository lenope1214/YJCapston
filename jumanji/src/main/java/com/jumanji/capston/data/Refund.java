//package com.jumanji.capston.data;
//
//import lombok.Getter;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
//
//@Getter
//@Entity
//@Table(name="refunds")
//public class Refund implements Serializable {
//    @Id
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "payments_id")
//    private Payment id;
//
//    private char refunded = 'Y';
//}
