package com.jumanji.capston.service.interfaces;

import com.jumanji.capston.data.Bucket;
import com.jumanji.capston.data.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public ResponseEntity<?> get(String userId);
    public ResponseEntity<?> getList();
    public ResponseEntity<?> post(User.Request request);
    public ResponseEntity<?> patch(User.Request request);
    public ResponseEntity<?> delete(String userId);
}