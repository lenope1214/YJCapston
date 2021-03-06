package com.jumanji.capston.config.auth;


import com.jumanji.capston.data.User;
import com.jumanji.capston.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

// 얘는 언제 발동을 하는가?
// SecurityConfig에서 loginProcessiongUrl("/login"); 으로 설정했기 때문에
// /login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loaduserByusername 함수가 실행.

@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;



    // 매개변수가 username 인데 input 태그의 name="username"이 아니라면 안됨. name속성값이랑 매칭.
    // username말고 스려면 SecurityConfig 에서 .loginPage 밑에 .usernameParameter("속성값") 으로 변경
    // 시큐리티 session(내부 Authentication(내부 UserDetails))
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userEntity = userRepository.findById(username);
        if(userEntity.isEmpty()) {
            return null;
        }

        return new PrincipalDetails(userEntity.get());
    }
}
