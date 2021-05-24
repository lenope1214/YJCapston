package com.jumanji.capston.controller;

import com.jumanji.capston.data.Shop;
import com.jumanji.capston.data.UserShopMark;
import com.jumanji.capston.service.UserShopMarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class UserShopMarkController {
    private final UserShopMarkService usmService;

    @Transactional(readOnly = true)
    @GetMapping("marks")
    public ResponseEntity<?> getMyMarkList(@RequestHeader String authorization){
        List<Shop> markShopList = usmService.getList(authorization);
        UserShopMark.Response response = new UserShopMark.Response(markShopList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("marks")
    public ResponseEntity<?> post(@RequestHeader String authorization, @RequestBody UserShopMark.Request request){
//        UserShopMark usm =
                usmService.post(authorization,request);
//        UserShopMark.Response response = new UserShopMark.Response(usm);
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Transactional
    @DeleteMapping("marks/{shopId}")
    public ResponseEntity<?> delete(@RequestHeader String authorization, @PathVariable String shopId){
        usmService.delete(authorization, shopId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
