package com.codesample.mymug.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.codesample.mymug.data.User;
@Repository
public interface UserRepository extends JpaRepository<User, String>{

}