package com.jumanji.capston.service;

import com.jumanji.capston.data.Reservation;
import com.jumanji.capston.repository.OrderMenuRepository;
import com.jumanji.capston.service.interfaces.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.List;

@Service
public class ReservationServiceImpl implements BasicService<Reservation, Reservation.Request>{
    @Autowired
    OrderMenuRepository orderRepository;


    @Override
    public Reservation get(@Nullable String authorization, String... str) {
        return null;
    }

    @Override
    public List<Reservation> getList(@Nullable String authorization, String... str) {
        return null;
    }

    @Override
    public Reservation post(@Nullable String authorization, Reservation.Request request) {
        return null;
    }

    @Override
    public Reservation patch(@Nullable String authorization, Reservation.Request request) {
        return null;
    }

    @Override
    public void delete(@Nullable String authorization, String... str) {

    }

    @Override
    public Reservation isPresent(String id) {
        return null;
    }

    @Override
    public boolean isEmpty(String id) {
        return false;
    }
}
