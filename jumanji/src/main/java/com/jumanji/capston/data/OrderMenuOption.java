package com.jumanji.capston.data;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Builder
public class OrderMenuOption {


}

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
class MessageId implements Serializable {
    @Column(name="reg_time")
    private Timestamp regTime;//시간
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",nullable = false)
    @JsonIgnore
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="shop_id",nullable = false)
    @JsonIgnore
    private Shop shop;

}