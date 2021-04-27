package com.jumanji.capston.service;

import com.jumanji.capston.data.OrderMenu;
import com.jumanji.capston.data.Reservation;
import com.jumanji.capston.repository.OrderMenuRepository;
import com.jumanji.capston.service.interfaces.BasicService;
import com.jumanji.capston.service.interfaces.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl
//        implements ReservationService, BasicService
{
    @Autowired
    OrderMenuRepository orderRepository;

//    public Reservation findById(String id){
//        OrderMenu order = orderRepository.findById(id)
//                .orElseThrow(()-> new IllegalArgumentException("id를 확인해주세요!!!"));
//
//        return reservationRepository.findById(order)
//                .orElseThrow(()-> new IllegalArgumentException("Order를 제대로 못가져옴! 확인해주세요!!!"));
//
//    }
//
//
//    public Reservation insert(Reservation _reservation){
//        return reservationRepository.save(_reservation);
//    }
//
//    @Override
//    public ResponseEntity<?> get(String reservationId) {
//        return null;
//    }
//
//    @Override
//    public ResponseEntity<?> getList() {
//        return null;
//    }
//
//    @Override
//    public ResponseEntity<?> post(Reservation.Request request) {
//        return null;
//    }
//
//    @Override
//    public ResponseEntity<?> patch(Reservation.Request request) {
//        return null;
//    }
//
//    public ResponseEntity<?> delete(String order_id){
//        OrderMenu order = orderRepository.findById(order_id).orElseThrow(()-> new IllegalArgumentException("주문번호를 확인해주세요!!!"));
//        Reservation reservation = reservationRepository.findById(order)
//                .orElseThrow(()-> new IllegalArgumentException("Order를 제대로 못가져옴! 확인해주세요!!!"));
//        reservationRepository.delete(reservation);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    public List<Reservation> findAll() {
//        return reservationRepository.findAll();
//    }
//
//    @Override
//    public boolean isPresent(String id) {
//        return false;
//    }
//
//    @Override
//    public boolean isEmpty(String id) {
//        return false;
//    }
}
