package com.jumanji.capston.service.interfaces;

import com.jumanji.capston.data.Bucket;
import com.jumanji.capston.data.Shop;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface ShopService {
    public ResponseEntity<?> get(String shopId);
    public ResponseEntity<?> getList();
    public ResponseEntity<?> post(Shop.Request request);
    public ResponseEntity<?> patch(Shop.Request request);
    public ResponseEntity<?> delete(String shopId);
}
