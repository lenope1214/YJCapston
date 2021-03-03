package com.jumanji.capston.repository;

import com.jumanji.capston.data.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

}

//package com.jumanji.capston.repository;
//
//import com.jumanji.capston.data.Member;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface MemberRepository extends JpaRepository<Member, String> {
//
//}