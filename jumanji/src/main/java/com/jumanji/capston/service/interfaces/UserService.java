package com.jumanji.capston.service.interfaces;

import com.jumanji.capston.data.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    public User get(String userId);
    public List<User> getList(String authorization);
    public User post(User.Request request);
    public User patch(String authorization, User.Request request);
    public void delete(String userId);
}