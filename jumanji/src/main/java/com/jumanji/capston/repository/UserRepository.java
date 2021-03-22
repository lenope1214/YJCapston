package com.jumanji.capston.repository;

import com.jumanji.capston.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Repository 어노테이션이 없어도 JpaRepository를 상속했기 때문에 가능...?
//@Repository //                                         데이터타입, 기본키 타입
// 자바는 _ underscore 가 아닌 카멜 표기라 밑 함수에 사용하면 오류..
public interface UserRepository extends JpaRepository<User, String> {
//    User findByUsername(String username);
//    void add(Member member);

    // SELECT * FROM user WHERE provider = ?1 and providerId = ?2
    Optional<User> findByProviderAndProviderId(String provider, String providerId);
}
