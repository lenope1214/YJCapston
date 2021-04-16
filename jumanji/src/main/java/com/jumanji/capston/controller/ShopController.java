package com.jumanji.capston.controller;



import com.jumanji.capston.data.Shop;
import com.jumanji.capston.service.ShopServiceImpl;
import com.jumanji.capston.service.StorageServiceImpl;
import com.jumanji.capston.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class ShopController  {
//    Logger logger;


    @Autowired
    ShopServiceImpl shopService;

    @Autowired
    UserServiceImpl userService;


    @Autowired
    StorageServiceImpl storageService;


    @Transactional(readOnly = true) // get /shop/{shopId}
    @GetMapping("/shop/{shopId}")  // get shop/{shopId} 식당번호로 식당 조회
    public ResponseEntity<?> getShopById(@PathVariable String shopId) {
        return shopService.getShopByShopId(shopId);
    }

//    @Transactional(readOnly = true)
//    @GetMapping("/shopIntro/{shopId}") // get /shopIntro/{shopId}
//    public ResponseEntity<?> getShopIntro(@PathVariable String shopId) {
//        return shopService.getShopIntro(shopId);
//    }

    @Transactional(readOnly = true)
    @GetMapping("/myShop") // get /myShop
    public ResponseEntity<?> getMyShop(@RequestHeader String authorization) { // 수정해야함.
        return shopService.getMyShop(authorization);

    }

    @Transactional(readOnly = true)
    @GetMapping("/shopList") // get /shopList
    public ResponseEntity<?> selectShopList() {
        System.out.println("샵리스트 >> ");
        return shopService.getShopList();
//        return shopService.getShopList(category, sortTarget);

    }

    @Transactional(readOnly = true)
    @GetMapping("/shopList/{category}") // get /shopList/{category}
    public ResponseEntity<?> getShopListByCategory(@PathVariable String category) {
        return shopService.getShopListByCat(category);
//        return new ResponseEntity<>(shopCatList, httpHeaders, HttpStatus.OK); // 이렇게 하면 오류. 객체를 utf로 변환 시켜서 그런지 무슨 한글 변환하면서 오류나나봄!
    }

    @Transactional
    @PostMapping("/shop") // post /shop 매장등록     Form-data로 받음 => Param. requestbody를 안적으면 자동 param 매핑 해주는듯
    public ResponseEntity<?> postShop(Shop.Request request, @RequestHeader String authorization) throws ParseException {
        return shopService.postShop(request, authorization);
    }

    @Transactional // delete
    @DeleteMapping("/shop/{shopId}")
    public ResponseEntity<?> deleteShop(@RequestHeader String authorization, @PathVariable String shopId) {
        return shopService.delete(authorization, shopId);
    }

    @Transactional
    @PutMapping("/shop")
    public ResponseEntity<?> putShop(@RequestHeader String authorization, @RequestBody Shop.Request shop) {
        return shopService.putShop(authorization, shop);
    }

    @Transactional
    @PatchMapping("/shop") // patch /shop
    public ResponseEntity<?> patchShop(@RequestHeader String authorization, @RequestBody Shop.Request request) {
        return shopService.patchShop(authorization, request);
    }

    @Transactional
    @PatchMapping("/shop/{shopId}/open")
    public ResponseEntity<?> updateShopIsOpen(@RequestHeader String authorization, @PathVariable String shopId) {
        return shopService.patchShopIsOpen(authorization, shopId);
    }


    //
    @Transactional
    @PatchMapping("/shop/{shopId}/reserve")
    public ResponseEntity<?> updateShopIsRsPos(@RequestHeader String authorization, @PathVariable String shopId) {
        return shopService.patchSHopIsRsPos(authorization, shopId);
    }

//    private List<Shop> getMyShopList(String loginId) {
//        return shopService.findByOwnerId(loginId);
//    }


}
