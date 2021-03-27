package com.jumanji.capston.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/samples")
@RequiredArgsConstructor
@Slf4j
public class SampleController {

//    private static final Logger logger = LogManager.getLogger(SampleController.class);
//
//    @GetMapping
//    public ResponseEntity<String> create(@RequestParam("name") String request) {
//        logger.info("Hello. This is LogManager's logger");
//        log.info("Hello. This is Lombok's logger");
//        System.out.println(logger.equals(log));
//        return ResponseEntity.ok(request);
//    }
}