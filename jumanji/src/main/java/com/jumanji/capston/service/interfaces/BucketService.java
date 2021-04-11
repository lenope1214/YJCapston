package com.jumanji.capston.service.interfaces;

import com.jumanji.capston.data.Bucket;
import org.springframework.http.ResponseEntity;

/** bucket service Interface **/


public interface BucketService{

    public ResponseEntity<?> get(String bucketId);
    public ResponseEntity<?> getList(String userId);
    public ResponseEntity<?> post(Bucket.Request request);
    public ResponseEntity<?> patch(Bucket.Request request);
    public ResponseEntity<?> delete(String bucketId);
}
