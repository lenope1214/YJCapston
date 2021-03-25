package com.jumanji.capston.data;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Getter
@Entity
@Table(name="reservations")
public class Reservation implements Serializable {
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name="order_id")
    private Order order; //아이디
    private int people; // 사람 수
    private char delay; // 이름
    @Column(name = "arrive_time")
    private Timestamp arriveTime; // 가게 도착시간
}
