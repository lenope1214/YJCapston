package com.jumanji.capston.service.interfaces;

import com.jumanji.capston.data.Message;
import org.springframework.http.ResponseEntity;

public interface MessageService {
    public ResponseEntity<?> get(String messageId);
    public ResponseEntity<?> post(Message.Request request);
    public ResponseEntity<?> patch(Message.Request request);
    public ResponseEntity<?> delete(String messageId);
}
