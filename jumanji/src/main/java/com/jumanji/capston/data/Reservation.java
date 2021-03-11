package com.jumanji.capston.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name="reservations")
public class Reservation implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name="order_id")
    private Order order_id; //아이디
    private int people; // 사람 수
    private char delay; // 이름
    private Timestamp arrive_time; // 가게 도착시간
}
