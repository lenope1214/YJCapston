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
    private Date begin_date= new Date(); // 사용 마지막 기간
    private Date expiry_date; // 만료기간
}

