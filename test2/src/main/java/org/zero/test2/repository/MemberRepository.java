package org.zero.test2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zero.test2.member.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
