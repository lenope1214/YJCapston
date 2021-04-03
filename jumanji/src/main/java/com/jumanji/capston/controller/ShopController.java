package com.jumanji.capston.controller;

import com.jumanji.capston.config.jwt.JwtTokenUtil;
import com.jumanji.capston.controller.exception.ApiErrorResponse;
import com.jumanji.capston.controller.exception.ShopException.ShopNotFoundException;
import com.jumanji.capston.data.Shop;
import com.jumanji.capston.data.User;
import com.jumanji.capston.service.ShopService;
import com.jumanji.capston.service.StorageService;
import com.jumanji.capston.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class ShopController {
//    Logger logger;


    @Autowired
    ShopService shopService;

    @Autowired
    UserService userService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    StorageService storageService;

    @Autowired
    HttpHeaders httpHeaders;


    @Transactional(readOnly = true)
    @GetMapping("/shop/{shopId}")  // get shop/{shopId} 식당번호로 식당 조회
    public ResponseEntity<?> getShopById(@PathVariable String shopId) {
        Shop shop;
        System.out.println("ShopController in getShopById");
        System.out.println("shop id : " + shopId);
        try {
            shop = shopService.findById(shopId);

        } catch (ShopNotFoundException e) {
            return new ResponseEntity<>(new ApiErrorResponse(e.getCode(), e.getId()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(shop, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/shopIntro/{shopId}")
    public ResponseEntity<?> getShopIntro(@PathVariable String shopId) {
        return new ResponseEntity<>(shopService.getShopIntro(shopId), httpHeaders, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/myShop")
    public ResponseEntity<?> getMyShop(@RequestHeader String authorization) {
        System.out.println("ShopController in getMyShop");
        String loginId = jwtTokenUtil.getUsername(authorization);
        User userEntity = userService.findById(loginId);
        if (userEntity == null) return new ResponseEntity<>("로그인 되어있지 않습니다.", httpHeaders, HttpStatus.BAD_REQUEST);
        System.out.println("요청접속 유저 ID : " + userEntity.getId());
        List<Shop> result = shopService.haveShop(userEntity.getId());
        if (result == null) return new ResponseEntity<>("매장 등록이 되어있지 않습니다.", httpHeaders, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/shopList")
    public ResponseEntity<?> getShopList() {
        System.out.println("샵리스트 >> ");
        List<Shop> shopList = shopService.findAll();
        return new ResponseEntity<>(shopList, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/shopList/{category}")
    public ResponseEntity<?> getShopListByCategory(@PathVariable String category) {
        System.out.println("캣 : " + category);
        List<Shop> shopCatList = shopService.findByCat(category);
        if (shopCatList.size() != 0) {
            System.out.println("샵캣리스트 :" + shopCatList.get(0));
            return new ResponseEntity<>(shopCatList, HttpStatus.OK);
        }
        return new ResponseEntity<>("매장이 없습니다.", httpHeaders, HttpStatus.OK);

//        return new ResponseEntity<>(shopCatList, httpHeaders, HttpStatus.OK); // 이렇게 하면 오류. 객체를 utf로 변환 시켜서 그런지 무슨 한글 변환하면서 오류나나봄!
    }

    @Transactional
    @PostMapping("/shop") // 매장등록
    public ResponseEntity<?> insertShop(Shop.info shopInfo, @RequestHeader String authorization) throws ParseException {
        System.out.println("Auth : " + authorization);
        String loginId = jwtTokenUtil.getUsername(authorization);
        System.out.println("로그인 id : " + loginId);
        User userEntity = userService.findById(loginId);
        System.out.println("매장등록 요청 ID : " + userEntity.getId());
//        logger.log(Level.INFO, "open time and close time\n" + shop.getOpenTime() +"\n" + shop.getCloseTime());

        System.out.println("shopInfo.toString() : " + shopInfo.toString());
        String uri = "shop\\" + shopInfo.getId() +"\\thumbnail\\";
        String imgPath = storageService.store(shopInfo.getImg(), uri, shopInfo.getImg().getName());
        Shop shopEntity =
                Shop.createShop()
                        .shopId(shopInfo.getId())
                        .name(shopInfo.getName())
                        .intro(shopInfo.getIntro())
                        .openTime(shopInfo.getOpenTime())
                        .closeTime(shopInfo.getCloseTime())
                        .address(shopInfo.getAddress())
                        .addressDetail(shopInfo.getAddressDetail())
                        .category(shopInfo.getCategory())
                        .imgPath(imgPath)
                        .build();
        Object result = shopService.insert(shopEntity, userEntity);
        if (result.getClass() == Shop.class) return new ResponseEntity<>(result, HttpStatus.CREATED);
        else if (result.equals("duplicate"))
            return new ResponseEntity<>("사업자 번호가 중복입니다.", httpHeaders, HttpStatus.BAD_REQUEST);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

//    @Transactional
//    @DeleteMapping("/shop")
//    public ResponseEntity<?> deleteShop(@RequestBody String id) {
//        return new ResponseEntity<>(shopService.delete(id), HttpStatus.OK);
//    }

//    @Transactional
//    @PutMapping("/shop")
//    public ResponseEntity<?> putShop(@RequestBody Shop shop){
//        return new ResponseEntity<>(shopService.insertShop(shop), HttpStatus.OK);
//    }

    @Transactional
    @PatchMapping("/shop")
    public ResponseEntity<?> updateShopInfo(@RequestBody Shop.Patch patch) {
        Shop shop;
        System.out.println("patch.getShopId() : " + patch.getShopId());
        try {
            shop = shopService.findById(patch.getShopId());
        } catch (ShopNotFoundException e) {
            return new ResponseEntity<>(new ApiErrorResponse(e.getCode(), e.getId()), HttpStatus.NOT_FOUND);
        }
        shop.update(patch);

        return null;
    }

    @Transactional
    @PatchMapping("/shop/{shopId}/open")
    public ResponseEntity<?> updateShopIsOpen(@RequestHeader String authorization, @PathVariable String shopId) {
        List<Shop> shopList = getLoginUserShop(authorization);
        if (shopList.isEmpty()) return new ResponseEntity<>("매장이 없습니다.", httpHeaders, HttpStatus.BAD_REQUEST);
        for (Shop shop : shopList) {
            if (shop.getId().equals(shopId)) {                // 해당 유저의 해당 매장번호가 있음!
                System.out.println("반전성공");
                return new ResponseEntity<>(shopService.updateIsOpen(shop), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("매장번호가 일치하는 매장이 없습니다.", httpHeaders, HttpStatus.BAD_REQUEST);
    }


    //
    @Transactional
    @PatchMapping("/shop/{shopId}/reserve")
    public ResponseEntity<?> updateShopIsRsPos(@RequestHeader String authorization, @PathVariable String shopId) {
        List<Shop> shopList = getLoginUserShop(authorization);
        if (shopList.isEmpty()) return new ResponseEntity<>("매장이 없습니다.", httpHeaders, HttpStatus.BAD_REQUEST);
        for (Shop shop : shopList) {
            if (shop.getId().equals(shopId)) {
                // 해당 유저의 해당 매장번호가 있음!
                System.out.println("반전성공");
                return new ResponseEntity<>(shopService.updateIsRsPos(shop), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("매장번호가 일치하는 매장이 없습니다.", httpHeaders, HttpStatus.BAD_REQUEST);
    }

    private List<Shop> getLoginUserShop(String id) {
        String userId = jwtTokenUtil.getUsername(id);
//        System.out.println("userId : " + userId);
        return shopService.findByOwnerId(userId);
    }
}
