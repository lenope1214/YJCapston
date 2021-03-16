package wlh.wickies.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import wlh.wickies.restapi.model.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select m.userid from Member m where m.userid = :userid")
    Object findByMemberId(@Param("userid") String userid);
}
