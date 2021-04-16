package com.jumanji.capston.service.interfaces;

import com.jumanji.capston.data.Tab;
import org.springframework.http.ResponseEntity;

public interface TableService {
    public ResponseEntity<?> get(String tableId);
    public ResponseEntity<?> getList(String shopId);
    public ResponseEntity<?> post(Tab.Request request);
    public ResponseEntity<?> patch(Tab.Request request);
    public ResponseEntity<?> delete(String tableId);
}
