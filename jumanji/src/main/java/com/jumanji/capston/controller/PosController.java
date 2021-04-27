package com.jumanji.capston.controller;

import com.jumanji.capston.data.OrderMenu;
import com.jumanji.capston.data.Pos;
import com.jumanji.capston.data.Shop;
import com.jumanji.capston.data.Tab;
import com.jumanji.capston.service.OrderMenuServiceImpl;
import com.jumanji.capston.service.ShopServiceImpl;
import com.jumanji.capston.service.TableServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PosController {
    @Autowired
    ShopServiceImpl shopService;
    @Autowired
    OrderMenuServiceImpl orderMenuService;
    @Autowired
    TableServiceImpl tableService;


    @Transactional(readOnly = true)
    @GetMapping("/shop/{shopId}/pos")
    public ResponseEntity<?> getShopPos(@PathVariable String shopId){
        Pos response = null;
//        Shop shop;
//        List<OrderMenu> orderMenuList;
//        List<Tab> tabList;
//
//        shop = shopService.get(shopId);
//        orderMenuList = orderMenuService.


        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
