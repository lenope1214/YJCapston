package com.jumanji.capston.service;

import com.jumanji.capston.data.DateOperator;
import com.jumanji.capston.data.Shop;
import com.jumanji.capston.data.User;
import com.jumanji.capston.repository.ShopRepository;
import com.jumanji.capston.repository.UserRepository;
import com.jumanji.capston.service.exception.ApiErrorResponse;
import com.jumanji.capston.service.exception.Auth.ForbiddenException;
import com.jumanji.capston.service.exception.ShopException.ShopHasExistException;
import com.jumanji.capston.service.exception.ShopException.ShopNotFoundException;
import com.jumanji.capston.service.interfaces.BasicService;
import com.jumanji.capston.service.interfaces.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService, BasicService {
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    StorageServiceImpl storageService;

    @Autowired
    HttpHeaders httpHeaders;


    public List<Shop> getShopListByOwnerId(String id) {
        List<Shop> shopList = shopRepository.findAllByOwner_Id(id);
        if (shopList.size() == 0) throw new ShopNotFoundException();
        return shopList;
    }

    public char reverseIsOpen(Shop shop) {
        if (shop.getIsOpen() == 'Y') shop.setIsOpen('N');
        else shop.setIsOpen('Y');
        shopRepository.save(shop);
        return shop.getIsOpen();
    }

    public char reverseIsRsPos(Shop shop) {
        if (shop.getIsRsPos() == 'Y') shop.setIsRsPos('N');
        else shop.setIsRsPos('Y');
        shopRepository.save(shop);
        return shop.getIsRsPos();
    }


    public char patchShopIsOpen(String authorization, String shopId) {
        String loginId = userService.getMyId(authorization);
        isPresent(shopId);
        isOwnShop(loginId, shopId);
        Shop shop = shopRepository.findById(shopId).get();
        return reverseIsOpen(shop);
    }

    public char patchSHopIsRsPos(String authorization, String shopId) {
        String loginId = userService.getMyId(authorization);
        isPresent(shopId);
        isOwnShop(loginId, shopId);
        Shop shop = shopRepository.findById(shopId).get();
        return reverseIsRsPos(shop);
    }

    public ResponseEntity<?> getShopByShopId(String shopId) {
        Shop shop;
        System.out.println("ShopController in getShopById");
        System.out.println("shop id : " + shopId);
        isPresent(shopId);
        try {
            shop = shopRepository.findById(shopId).get();
        } catch (ShopNotFoundException e) {
            return new ResponseEntity<>(new ApiErrorResponse(e.getCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        Shop.Response response = new Shop.Response(shop);
//        if(shop.getImgPath()!=null)response.setImg(storageService.loadImg(shop.getImgPath()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    public List<Shop> getMyShop(String authorization) {
        System.out.println("ShopController in getMyShop");
        String loginId = userService.getMyId(authorization);
        User userEntity = userRepository.findById(loginId).get();
        List<Shop> result = getShopListByOwnerId(userEntity.getId());
        return result;
    }

    @Override
    public Shop get(String shopId) {
        isPresent(shopId);
        return shopRepository.findById(shopId).get();
    }

    @Override
    public ResponseEntity<?> getList(String category, String sortTarget) {
        if (getShopListSize() != 0) {
            List<Shop.Response> responseList = new ArrayList<>();
            sortTarget = sortTarget == null ? "" : sortTarget;
            category = category == null ? "" : category;
            switch (sortTarget) {
                case "score":
                    return new ResponseEntity<>(shopRepository.ShopOrderByScore(category), HttpStatus.OK);
                default:
                    List<Shop> shopList = shopRepository.findAll();
                    for (Shop shop : shopList) {
                        Shop.Response response = new Shop.Response(shop);
                        responseList.add(response);
                    }
                    break;
            }
            // shop.response로 parsing 해서 보내기.

            return new ResponseEntity<>(responseList, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public Shop post(String authorization, Shop.Request request) {
        System.out.println("매장등록's shopId : " + request.getId());

        String loginId = userService.getMyId(authorization);
        User user = userService.get(loginId);
        String uri = "shop/" + request.getId() + "/thumbnail/";
        String imgPath = null;
        System.out.println("openTime : " + request.getOpenTime());
        System.out.println("closeTime : " + request.getCloseTime());

        // 유효성 검사
        isPresent(request.getShopId()); // 해당 사업자 번호로 사업자 등록이 됐는지 확인
        userService.isPresent(loginId); // 해당 요청하는 사람이 존재하는지
        userService.isAuth(user.getRole(), "OWNER");


        if (request.getImg() != null)
            imgPath = storageService.store(request.getImg(), request.getImg().getName(), uri.split("/"));
        Date openTime = DateOperator.stringToMilisecond(request.getOpenTime());
        Date closeTime = DateOperator.stringToMilisecond(request.getCloseTime());
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
                .owner(user)
                .build();
        Shop shop = shopRepository.save(shopEntity);
        return shop;
    }

    @Override
    public Shop patch(String authorization, Shop.Request request) {
        System.out.println("patch.getShopId() : " + request.getShopId());
        String userId = userService.getMyId(authorization);
        Shop shop = shopRepository.findById(request.getShopId()).get();
        isPresent(request.getShopId());
        isOwnShop(userId, shop.getId());


        System.out.println(
                "매장 수정 patch.toString()" + "\n" +
                        "patch.getOpenTime : " + request.getOpenTime() + "\n" +
                        "patch.getCloseTime : " + request.getCloseTime()
        );
        shop.update(request);
        return shop;
    }

    @Override
    public void delete(String authorization, String shopId) {
        String loginId = userService.getMyId(authorization); // jwt가 있다는 것은 유저 인증이 완료. isPresent 필요 없음.
        Shop shopEntity = shopRepository.findById(shopId).get();
        isOwnShop(loginId, shopId);
        shopRepository.delete(shopEntity);
    }

    // 있는 매장번호 인지 확인. 없으면 error를 반환하게 된다.
    public boolean isPresent(String shopId) {
        if (shopRepository.findById(shopId).isPresent()) return true;
        throw new ShopNotFoundException();
    }

    @Override
    public boolean isEmpty(String shopId) {
        if (shopRepository.findById(shopId).isEmpty()) return true;
        throw new ShopHasExistException();
    }

    public boolean isOwnShop(String loginId, String shopId) {
        System.out.println("보유매장 비교 유저아디 : " + loginId);
        System.out.println("보유매장 비교 매장번호 : " + shopId);
        User loginUser = userService.get(loginId);
        System.out.println("로긘 유저 이름 : " + loginUser.getName());
        for (Shop shop : shopRepository.findByOwnerId(loginId)) {
            if (shop.getId().equals(shopId)) {
                System.out.println("보유매장 매칭된 매장번호 : " + shop.getId());
                return true;
            }
        }
        throw new ForbiddenException();
    }

    public int getShopListSize() {
        return shopRepository.findAll().size();
    }
}
