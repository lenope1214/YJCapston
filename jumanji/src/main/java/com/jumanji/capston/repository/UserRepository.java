package com.jumanji.capston.repository;

import com.jumanji.capston.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository 어노테이션이 없어도 JpaRepository를 상속했기 때문에 가능.
@Repository //                                         데이터타입, 기본키 타입
public interface UserRepository extends JpaRepository<User, String> {
//    User findByUsername(String username);
//    void add(Member member);
}
