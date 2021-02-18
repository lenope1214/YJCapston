package com.codesample.mymug.repository;

import com.codesample.mymug.data.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Integer> {
    List<Reply> findByPno_Pno(long pno); // findBy foreignKey Feild(첫 대문자)_가져온테이블의ID

    Reply findByRno(long rno);
    //findByforeign https://penpen.tistory.com/entry/Spring-Data-JPA-Repository-%EB%A9%94%EC%86%8C%EB%93%9C-%EC%BF%BC%EB%A6%AC-FK-%EB%A1%9C-findBy-%EB%A7%8C%EB%93%A4%EA%B8%B0

//    int findCountByPno(List<Board> boards);
//
//    List<Integer> CountByPnoEquals(int pno);
}