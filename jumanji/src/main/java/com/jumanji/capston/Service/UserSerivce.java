package com.jumanji.capston.Service;

import com.jumanji.capston.data.User;
import com.jumanji.capston.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserSerivce {
    private final UserRepository userRepository;

    @Transactional
    public User findById(String id){
        return userRepository.findById(id).get();
    }

    public
}
