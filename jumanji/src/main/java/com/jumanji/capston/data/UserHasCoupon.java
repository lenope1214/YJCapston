package com.jumanji.capston.data;

import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Entity
@Table(name="userHasCoupon")
public class UserHasCoupon {

    @EmbeddedId
    private UserHasCouponId id;
    @Column(name = "begin_date")
    private Date beginDate= new Date(); // 사용 마지막 기간
    @Column(name = "expiry_date")
    private Date expiryDate; // 만료기간
}

