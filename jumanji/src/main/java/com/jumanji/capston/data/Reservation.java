package com.jumanji.capston.data;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;


@Getter
@Entity
@Table(name="reservations")
public class Reservation {
    @Id
    @OneToOne
    @JoinColumn(name="order_id")
    private Order order_id; //아이디
    private int people; // 사람 수
    private char delay; // 이름
    private Timestamp arrive_time; // 가게 도착시간
}
