package com.jumanji.capston.controller.externalApiController;

import com.jumanji.capston.service.AddressSearchApiService;
import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    @Getter@Setter @NoArgsConstructor
    class AddrParameters{
        private String keyword;
        private String currentPage;
        private String countPerPage;
    }

    @GetMapping("/searchAddr")
    public ResponseEntity<?> getAddressResult(AddrParameters parameters){
//        public ResponseEntity<?> getAddressResult(@RequestBody SearchAddressRequest request){
//        String currentPage = request.getCurrentPage();
//        String countPerPage = request.getCountPerPage();
//        String keyword = request.getKeyword();

        System.out.println("주소 검색 요청 결과 =>" +
                "keyword : " + parameters.getKeyword() +"\n" +
                "currentPage : " + parameters.getCurrentPage() +"\n" +
                "countPerPage : " + parameters.getCountPerPage()
        );

        String confmKey = "U01TX0FVVEgyMDIxMDMxNzE3MjcxNjExMDkzMzY=";
        String resultType = "json";
        String apiUrl = "https://www.juso.go.kr/addrlink/addrLinkApi.do?currentPage=";
        apiUrl += (parameters.getCurrentPage() != null ? parameters.getCurrentPage() : "1") +"&countPerPage=" ;
        apiUrl += (parameters.getCountPerPage() != null ? parameters.getCountPerPage() : "10") + "&keyword=";
        apiUrl += (URLEncoder.encode(parameters.getKeyword(), StandardCharsets.UTF_8)) +"&confmKey="+confmKey+"&resultType="+resultType; // 요청 url
        System.out.println("완성된 url : " + apiUrl);
        return new ResponseEntity<>(addressSearchApiService.searchAddress(apiUrl), httpHeaders, HttpStatus.OK);
    }
}
