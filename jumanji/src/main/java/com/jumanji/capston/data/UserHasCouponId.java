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
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;//유저번호

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon; // 쿠폰번호
}
