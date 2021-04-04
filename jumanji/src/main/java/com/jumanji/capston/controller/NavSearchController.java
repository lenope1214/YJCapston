package com.jumanji.capston.controller;

import com.jumanji.capston.service.NaverPlaceSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NavSearchController {

    @Autowired
    NaverPlaceSearchService naverPlaceSearchService;

    @GetMapping("/addressApiTest/{keyword}")
    public ResponseEntity<?> callAddressApi(@PathVariable String keyword) {
//        System.out.println("Keyword : " + keyword);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=UTF-8");

        return new ResponseEntity<>(naverPlaceSearchService.searchPlace(keyword), responseHeaders, HttpStatus.OK);
    }

}
