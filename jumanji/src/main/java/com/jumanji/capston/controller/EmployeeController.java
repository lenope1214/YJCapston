package com.jumanji.capston.controller;

import com.jumanji.capston.data.Pos;
import com.jumanji.capston.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    ShopServiceImpl shopService;
    @Autowired
    OrderMenuServiceImpl orderMenuService;
    @Autowired
    TableServiceImpl tableService;
    @Autowired
    OrderServiceImpl orderService;
    @Autowired
    PosService posService;
    @Autowired
    UserServiceImpl userService;


    @Transactional(readOnly = true)
    @GetMapping("/shop/{shopId}/employee")
    public ResponseEntity<?> getShopPos(@RequestHeader String authorization, @PathVariable String shopId){
        List<Pos> posList = posService.getShopPos(shopId);
        return new ResponseEntity<>(posList, HttpStatus.OK);
    }
}
