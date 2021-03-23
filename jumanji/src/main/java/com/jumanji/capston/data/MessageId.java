package com.jumanji.capston.data;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.sql.Timestamp;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MessageId implements Serializable {
    private Timestamp reg_time;//시간
    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User userId;
    @ManyToOne
    @JoinColumn(name="shop_id",nullable = false)
    private Shop shopId;

}