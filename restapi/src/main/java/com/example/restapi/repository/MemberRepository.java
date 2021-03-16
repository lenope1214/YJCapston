package com.example.restapi.repository;

import com.example.restapi.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select m.userid from Member m where m.userid = :userid")
    Object findByMemberId(@Param("userid") String userid);

    @Query("select m from Member m where m.userid = :userid and m.userpw = :userpw")
    Member findLogin(@Param("userid") String userid, @Param("userpw") String userpw);

}
