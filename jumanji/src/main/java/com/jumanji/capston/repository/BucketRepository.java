package com.jumanji.capston.repository;

import com.jumanji.capston.data.Bucket;
import com.jumanji.capston.data.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepository extends JpaRepository<Bucket, String> {


}
