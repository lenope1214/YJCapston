package com.example.restapi.repository;

import com.example.restapi.model.Member;
import com.example.restapi.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, String> {
    @Query("select m.id from Member m where m.id = :id")
    Object findByMemberId(@Param("id") String id);

    @Query("select m from Member m where m.id = :id and m.pw = :pw")
    Member findLogin(@Param("id") String id, @Param("pw") String pw);

    @Query("select s.member_id from Shop s inner join s.member_id m where m.id = :id")
    Object findByShop(@Param("id") String id);

}
