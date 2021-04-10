package com.jumanji.capston.controller;

import com.jumanji.capston.service.interfaces.BucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/samples")
@RequiredArgsConstructor
public class BucketController {
    @Autowired
    BucketService bucketService;

}