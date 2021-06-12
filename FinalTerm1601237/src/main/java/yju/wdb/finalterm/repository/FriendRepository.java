package yju.wdb.finalterm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yju.wdb.finalterm.Entity.BaseEntity;

import java.util.List;

public interface FriendRepository extends JpaRepository<BaseEntity, Long> {
    List<BaseEntity> findByTitleContaining(String keyword);
}
