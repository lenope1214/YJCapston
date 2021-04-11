package com.jumanji.capston.service.interfaces;

import com.jumanji.capston.data.Penalty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;

public interface PenaltyService {
    public ResponseEntity<?> get(String penaltyId);
    public ResponseEntity<?> getList();
    public ResponseEntity<?> post(Penalty.Request request);
    public ResponseEntity<?> patch(Penalty.Request request);
    public ResponseEntity<?> delete(String penaltyId);
}
