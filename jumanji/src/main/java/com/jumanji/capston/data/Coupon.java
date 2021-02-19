package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="coupon")
public class Coupon {
    @Id
    @Column(length = 9)
    private String co_no;//쿠폰번호
    @Column(length = 30)
    private String co_name;//쿠폰이름
    @ManyToOne
    @JoinColumn(name="account")
    private Account id; //아이디
}
