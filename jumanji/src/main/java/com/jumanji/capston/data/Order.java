package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name="odr")
public class Order implements Serializable {
    @Id
    @Column(name="odr_no", length = 16)
    private String no ; // 주문번호 insert 할때 값 yyMMddhhmmss+sequence 설정해주기. 기본값으로 하는거 어렵넹
    @Column(length = 2, nullable = false)
    private int odr_quantity; // 메뉴 수량
    @Column(nullable = false)
    private Date odr_date; // 주문일시
    @Column(length = 60)
    private String odr_req; // 요청사항

}
