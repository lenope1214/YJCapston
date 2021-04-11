package com.jumanji.capston.service.interfaces;

import com.jumanji.capston.data.Bucket;
import com.jumanji.capston.data.Reservation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface ReservationService {
    public ResponseEntity<?> get(String reservationId);
    public ResponseEntity<?> getList();
    public ResponseEntity<?> post(Reservation.Request request);
    public ResponseEntity<?> patch(Reservation.Request request);
    public ResponseEntity<?> delete(String reservationId);
}
