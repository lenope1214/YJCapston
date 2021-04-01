package com.jumanji.capston.controller;

import com.jumanji.capston.service.AddressSearchApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/v1")
public class AddressSearchApiController {
    @Autowired
    HttpHeaders httpHeaders;

    @Autowired
    AddressSearchApiService addressSearchApiService;

    @GetMapping("/searchAddr")
    public ResponseEntity<?> getAddressResult(@RequestParam String keyword,
                                              @RequestParam String currentPage,
                                              @RequestParam String countPerPage){
//        public ResponseEntity<?> getAddressResult(@RequestBody SearchAddressRequest request){
//        String currentPage = request.getCurrentPage();
//        String countPerPage = request.getCountPerPage();
//        String keyword = request.getKeyword();
        System.out.println("주소 검색 요청 결과 =>" +
                "keyword : " + keyword +"\n" +
                "currentPage : " + currentPage +"\n" +
                "countPerPage : " + countPerPage
        );

        String confmKey = "U01TX0FVVEgyMDIxMDMxNzE3MjcxNjExMDkzMzY=";
        String resultType = "json";
        String apiUrl = "https://www.juso.go.kr/addrlink/addrLinkApi.do?currentPage="+currentPage
                +"&countPerPage="+countPerPage+"&keyword="+ URLEncoder.encode(keyword, StandardCharsets.UTF_8)
                +"&confmKey="+confmKey+"&resultType="+resultType; // 요청 url
        return new ResponseEntity<>(addressSearchApiService.searchAddress(apiUrl), httpHeaders, HttpStatus.OK);
    }
}
