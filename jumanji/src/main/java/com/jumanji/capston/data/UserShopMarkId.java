package com.jumanji.capston.data;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode @Getter
public class UserShopMarkId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;//유저번호

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop; // 쿠폰번호
}

