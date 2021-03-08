package wlh.wickies.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wlh.wickies.restapi.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
