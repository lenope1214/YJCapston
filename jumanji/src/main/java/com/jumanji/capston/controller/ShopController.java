package com.jumanji.capston.controller;

import com.jumanji.capston.controller.commons.Controller;
import com.jumanji.capston.controller.exception.ApiErrorResponse;
import com.jumanji.capston.controller.exception.ShopException.ShopHasExistException;
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
import java.util.Date;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class ShopController extends Controller {
//    Logger logger;


    @Autowired
    ShopService shopService;

    @Autowired
    UserService userService;


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
            return new ResponseEntity<>(new ApiErrorResponse(e.getCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        Shop.Response response = new Shop.Response(shop);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/shopIntro/{shopId}")
    public ResponseEntity<?> getShopIntro(@PathVariable String shopId) {
        return new ResponseEntity<>(shopService.getShopIntro(shopId), httpHeaders, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/myShop")
    public ResponseEntity<?> getMyShop(@RequestHeader String authorization) { // 수정해야함.
        System.out.println("ShopController in getMyShop");
        String loginId = getLoginUserId(authorization);
        User userEntity = userService.findById(loginId);
        if (userEntity == null) return new ResponseEntity<>("로그인 되어있지 않습니다.", httpHeaders, HttpStatus.BAD_REQUEST);
        System.out.println("요청접속 유저 ID : " + userEntity.getId());
        List<Shop> result = shopService.haveShop(userEntity.getId());
        if (result == null) return new ResponseEntity<>("매장 등록이 되어있지 않습니다.", httpHeaders, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/shopList")
    public ResponseEntity<?> selectShopList() {
        System.out.println("샵리스트 >> ");
        List<Shop> shopList = shopService.findAll();
        return new ResponseEntity<>(shopList, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/shopList/{category}")
    public ResponseEntity<?> selectShopListByCategory(@PathVariable String category) {
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
    @PostMapping("/shop") // 매장등록     Form-data로 받음 => Param.
    public ResponseEntity<?> insertShop(Shop.info request, @RequestHeader String authorization) throws ParseException {
        System.out.println("매장등록's shopId : " + request.getId());
        try {
            if(shopService.findById(request.getId()) != null) throw new ShopHasExistException();
        } catch (ShopNotFoundException ignored) { // null 이면 없는 사업자번호 이므로 된다 !!
        } catch (ShopHasExistException e) { // 사업자번호 중복 에러 체킹!!
            return new ResponseEntity<>(new ApiErrorResponse(e.getCode(), e.getMessage()), HttpStatus.LOCKED);
        }

//        if ( == null)
//            return new ResponseEntity<>("사업자 번호가 중복입니다.", httpHeaders, HttpStatus.BAD_REQUEST);

        String loginId = getLoginUserId(authorization);
        User userEntity = userService.findById(loginId);
        String uri = "shop/" + request.getId() + "/thumbnail/";
        String imgPath=null;
        System.out.println("openTime : " + request.getOpenTime());
        System.out.println("closeTime : " + request.getCloseTime());
        if(request.getImg() != null) imgPath = storageService.store(request.getImg(), request.getImg().getName(), uri.split("/"));
        Date openTime = Shop.stringToDate(request.getOpenTime());
        Date closeTime = Shop.stringToDate(request.getCloseTime());
        Shop shopEntity;
        shopEntity = Shop.insertShop()
                .id(request.getId())
                .name(request.getName().replace(" ", "_"))
                .intro(request.getIntro())
                .openTime(openTime)
                .closeTime(closeTime)
                .address(request.getAddress())
                .addressDetail(request.getAddressDetail())
                .category(request.getCategory())
                .imgPath(imgPath)
                .owner(userEntity)
                .build();
        Shop result = shopService.insert(shopEntity);
        Shop.Response response = new Shop.Response(result);
        return new ResponseEntity<>(response, HttpStatus.CREATED); // 생성이므로 201번을 리턴.
    }

    @Transactional
    @DeleteMapping("/shop/{shopId}")
    public ResponseEntity<?> deleteShop(@RequestHeader String authorization, @PathVariable String shopId) {
        String loginId = getLoginUserId(authorization);
        if(loginId.equals(shopService.findById(shopId).getOwner().getId())) return new ResponseEntity<>(shopService.delete(shopId), HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(new ApiErrorResponse("error-0000", "권한이 없습니다."), HttpStatus.FORBIDDEN);
    }

//    @Transactional
//    @PutMapping("/shop")
//    public ResponseEntity<?> putShop(@RequestBody Shop shop){
//        return new ResponseEntity<>(shopService.insertShop(shop), HttpStatus.OK);
//    }

    @Transactional
    @PatchMapping("/shop")
    public ResponseEntity<?> updateShopInfo(@RequestBody Shop.Request request) {
        Shop shop;
        System.out.println("patch.getShopId() : " + request.getShopId());
        try {
            shop = shopService.findById(request.getShopId());
        } catch (ShopNotFoundException e) {
            return new ResponseEntity<>(new ApiErrorResponse(e.getCode(), e.getMessage()), HttpStatus.NOT_FOUND);
        }
        System.out.println(
                "매장 수정 patch.toString()" +"\n" +
                "patch.getOpenTime : " + request.getOpenTime() +"\n" +
                        "patch.getCloseTime : " + request.getCloseTime()
        );
        shop.update(request);
        return new ResponseEntity<>(shop, HttpStatus.OK);
    }

    @Transactional
    @PatchMapping("/shop/{shopId}/open")
    public ResponseEntity<?> updateShopIsOpen(@RequestHeader String authorization, @PathVariable String shopId) {
        String loginId = getLoginUserId(authorization);
        List<Shop> shopList = getMyShopList(loginId);
        if (shopList.isEmpty()) return new ResponseEntity<>("매장이 없습니다.", httpHeaders, HttpStatus.BAD_REQUEST);
        for (Shop shop : shopList) {
            if (shop.getId().equals(shopId)) {                // 해당 유저의 해당 매장번호가 있음!
                System.out.println(shop.getName() + "의 오픈상태 반전성공");
                return new ResponseEntity<>(shopService.updateIsOpen(shop), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("매장번호가 일치하는 매장이 없습니다.", httpHeaders, HttpStatus.BAD_REQUEST);
    }


    //
    @Transactional
    @PatchMapping("/shop/{shopId}/reserve")
    public ResponseEntity<?> updateShopIsRsPos(@RequestHeader String authorization, @PathVariable String shopId) {
        String loginId = getLoginUserId(authorization);
        List<Shop> shopList = getMyShopList(loginId);
        if (shopList.isEmpty()) return new ResponseEntity<>("매장이 없습니다.", httpHeaders, HttpStatus.BAD_REQUEST);
        for (Shop shop : shopList) {
            if (shop.getId().equals(shopId)) {
                // 해당 유저의 해당 매장번호가 있음!
                System.out.println(shop.getName() + "의 예약상태 반전성공");
                return new ResponseEntity<>(shopService.updateIsRsPos(shop), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("매장번호가 일치하는 매장이 없습니다.", httpHeaders, HttpStatus.BAD_REQUEST);
    }

    private List<Shop> getMyShopList(String loginId) {
        return shopService.findByOwnerId(loginId);
    }


}
