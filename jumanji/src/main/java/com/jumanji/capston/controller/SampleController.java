package com.jumanji.capston.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/samples")
@RequiredArgsConstructor
public class SampleController  {

//    private static final Logger logger = LogManager.getLogger(SampleController.class);
//
//    @GetMapping
//    public ResponseEntity<String> create(@RequestParam("name") String request) {
//        logger.info("Hello. This is LogManager's logger");
//        log.info("Hello. This is Lombok's logger")c;
//        System.out.println(logger.equals(log));
//        return ResponseEntity.ok(request);
//    }
}