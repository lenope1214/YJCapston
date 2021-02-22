package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name="reservation")
public class Reservation implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name="odr")
    private Odr odr_id;//주문번호
    @Column(length=2)
    private int rs_cus_no;//인원
    @Column
    private Timestamp delay;//지연시간
    @Column(length=2)
    private char is_showed='N';//노쇼여부
    @Column(length=2)
    private char is_cancel='N';//취소여부
    @Column
    private Date rs_date;//예약일시
}