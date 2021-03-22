package com.jumanji.capston.controller.Temporary;

import com.jumanji.capston.service.NaverPlaceSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    NaverPlaceSearchService naverPlaceSearchService;

//    @GetMapping("/addressApiTest/{keyword}")
//    public String callAddressApi(@PathVariable String keyword) {
//        System.out.println("Keyword : " + keyword);
//        return naverPlaceSearchService.searchPlace(keyword);
//    }

}
