package com.jumanji.capston.service;

import com.jumanji.capston.data.Order;
import com.jumanji.capston.data.Reservation;
import com.jumanji.capston.repository.OrderRepository;
import com.jumanji.capston.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    OrderRepository orderRepository;

    public Reservation findById(String id){
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("id를 확인해주세요!!!"));

        return reservationRepository.findById(order)
                .orElseThrow(()-> new IllegalArgumentException("Order를 제대로 못가져옴! 확인해주세요!!!"));

    }


    public Reservation insert(Reservation _reservation){
        return reservationRepository.save(_reservation);
    }

    public String delete(String order_id){
        Order order = orderRepository.findById(order_id).orElseThrow(()-> new IllegalArgumentException("주문번호를 확인해주세요!!!"));
        Reservation reservation = reservationRepository.findById(order)
                .orElseThrow(()-> new IllegalArgumentException("Order를 제대로 못가져옴! 확인해주세요!!!"));
        reservationRepository.delete(reservation);
        return "ok";
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }
}
