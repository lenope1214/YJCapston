package com.jumanji.capston.data;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserHasCouponId implements Serializable {
    @JoinColumn
    @ManyToOne
    private User user_id;//유저번호
    
    @JoinColumn
    @ManyToOne
    private Coupon coupon_id; // 쿠폰번호
}

