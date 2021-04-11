package com.jumanji.capston.repository;

import com.jumanji.capston.data.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BucketRepository extends JpaRepository<Bucket, String> {

    List<Bucket> findALLByUser_Id(String user_Id);
}