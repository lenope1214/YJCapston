package com.jumanji.capston.service;

import com.jumanji.capston.data.DateOperator;
import com.jumanji.capston.data.Shop;
import com.jumanji.capston.data.User;
import com.jumanji.capston.repository.ShopRepository;
import com.jumanji.capston.repository.UserRepository;
import com.jumanji.capston.service.exception.ApiErrorResponse;
import com.jumanji.capston.service.exception.ShopException.ShopHasExistException;
import com.jumanji.capston.service.exception.ShopException.ShopNotFoundException;
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

    public ResponseEntity<?> delete(String authorization, String shopId){
        String loginId = userService.getMyId(authorization); // jwt가 있다는 것은 유저 인증이 완료. isPresent 필요 없음.
        Shop shopEntity = shopRepository.findById(shopId).get();
        if(loginId.equals(shopEntity.getOwner().getId())) {
            shopRepository.delete(shopEntity);
            return new ResponseEntity<>("delete success", HttpStatus.NO_CONTENT);
        }
        else
            return new ResponseEntity<>(new ApiErrorResponse("error-0000"), HttpStatus.FORBIDDEN);
    }

    public ResponseEntity<?> getShopList() {
        if(shopRepository.findAll().size() != 0) {
            // shop.response로 parsing 해서 보내기.
            ArrayList<Shop.Response> responseList = new ArrayList<>();
            for(Shop shop :  shopRepository.findAll()){
                Shop.Response response = new Shop.Response(shop);
                responseList.add(response);
            }
            return new ResponseEntity<>(responseList, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("1001", HttpStatus.NO_CONTENT);
    }



    public ResponseEntity<?> getShopIntro(String shopId){
//        System.out.println("요청 매장 id : " + shopId );
        if(shopRepository.findById(shopId).isPresent())
            return new ResponseEntity<>(shopRepository.findById(shopId).get().getIntro(), HttpStatus.OK);
        else{
            System.out.println("매장 id 오류");
            return new ResponseEntity<>(new ApiErrorResponse("1001"), HttpStatus.BAD_REQUEST);
        }
    }

    public List<Shop> getShopListByOwnerId(String id){
        List<Shop> shopList = shopRepository.findAllByOwner_Id(id);
        if(shopList.size() == 0)throw new ShopNotFoundException();
        return shopList;
    }

    public ResponseEntity<?> getShopListByCat(String category) {
        System.out.println("캣 : " + category);
        List<Shop> shopCatList = shopRepository.findByCategory(category);
        if (shopCatList.size() != 0) {
            System.out.println("샵캣리스트 :" + shopCatList.get(0));
            return new ResponseEntity<>(shopCatList, HttpStatus.OK);
        }
        return new ResponseEntity<>("매장이 없습니다.", httpHeaders, HttpStatus.BAD_REQUEST);
    }

    public char reverseIsOpen(Shop shop){
        if(shop.getIsOpen() == 'Y')shop.setIsOpen('N');
        else shop.setIsOpen('Y');
        shopRepository.save(shop);
        return shop.getIsOpen();
    }

    public char reverseIsRsPos(Shop shop){
        if(shop.getIsRsPos() == 'Y')shop.setIsRsPos('N');
        else shop.setIsRsPos('Y');
        shopRepository.save(shop);
        return shop.getIsRsPos();
    }

    public ResponseEntity<?> postShop(Shop.Request request, String authorization) {
        System.out.println("매장등록's shopId : " + request.getId());
        try {
            if(shopRepository.findById(request.getId()).isPresent()) throw new ShopHasExistException();
        } catch (ShopNotFoundException ignored) { // null 이면 없는 사업자번호 이므로 된다 !!
        } catch (ShopHasExistException e) { // 사업자번호 중복 에러 체킹!!
            return new ResponseEntity<>(new ApiErrorResponse(e.getCode(), e.getMessage()), HttpStatus.LOCKED);
        }

//        if ( == null)
//            return new ResponseEntity<>("사업자 번호가 중복입니다.", httpHeaders, HttpStatus.BAD_REQUEST);

        String loginId = userService.getMyId(authorization);
        User userEntity = userService.getUserInfo(loginId);
        String uri = "shop/" + request.getId() + "/thumbnail/";
        String imgPath=null;
        System.out.println("openTime : " + request.getOpenTime());
        System.out.println("closeTime : " + request.getCloseTime());
        if(request.getImg() != null) imgPath = storageService.store(request.getImg(), request.getImg().getName(), uri.split("/"));
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
                .owner(userEntity)
                .build();
        Shop result = shopRepository.save(shopEntity);
        Shop.Response response = new Shop.Response(result);
        return new ResponseEntity<>(response, HttpStatus.CREATED); // 생성이므로 201번을 리턴.
    }

    public ResponseEntity<?> putShop(String authorization, Shop.Request request) {
        isPresent(request.getShopId()); // 있는 매장번호 인가?
        String loginId = userService.getMyId(authorization);
        Shop shopEntity = shopRepository.findById(request.getShopId()).get();
        if(isOwnShop(loginId, shopEntity.getId())){ // 로그인한 아이디가 해당 매장을 가지고 있는가?
            shopEntity.update(request);
            return new ResponseEntity<>(shopEntity, HttpStatus.OK);
        }
        return new ResponseEntity<>("not defined error ", HttpStatus.BAD_REQUEST);
    }



    public ResponseEntity<?> patchShop(String authorization, Shop.Request request) {
        System.out.println("patch.getShopId() : " + request.getShopId());
        String userId = userService.getMyId(authorization);
        Shop shop = shopRepository.findById(request.getShopId()).get();
        isPresent(request.getShopId());
        isOwnShop(userId, shop.getId());


        System.out.println(
                "매장 수정 patch.toString()" +"\n" +
                        "patch.getOpenTime : " + request.getOpenTime() +"\n" +
                        "patch.getCloseTime : " + request.getCloseTime()
        );
        shop.update(request);
        return new ResponseEntity<>(shop, HttpStatus.OK);
    }

    public ResponseEntity<?> patchShopIsOpen(String authorization, String shopId) {
        String userId = userService.getMyId(authorization);
        List<Shop> shopList = getShopListByOwnerId(userId);
        isPresent(shopId);
        for (Shop shop : shopList) {
            if (shop.getId().equals(shopId)) {                // 해당 유저의 해당 매장번호가 있음!
                System.out.println(shop.getName() + "의 오픈상태 반전성공");
                return new ResponseEntity<>(reverseIsOpen(shop), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("매장번호가 일치하는 매장이 없습니다.", httpHeaders, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> patchSHopIsRsPos(String authorization, String shopId) {
        String userId = userService.getMyId(authorization);
        List<Shop> shopList = getShopListByOwnerId(userId);
        isPresent(shopId);
        for (Shop shop : shopList) {
            if (shop.getId().equals(shopId)) {
                // 해당 유저의 해당 매장번호가 있음!
                System.out.println(shop.getName() + "의 예약상태 반전성공");
                return new ResponseEntity<>(reverseIsRsPos(shop), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("매장번호가 일치하는 매장이 없습니다.", httpHeaders, HttpStatus.BAD_REQUEST);
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



    public boolean isOwnShop(String loginId, String shopId) {
        System.out.println("삭제 유저아디 : " + loginId);
        System.out.println("삭제 매장번호 : " + shopId);
        User loginUser = userService.getUserInfo(loginId);
        System.out.println("로긘 유저 이름 : " + loginUser.getName());
        for(Shop shop : shopRepository.findByOwnerId(loginId)){
            if(shop.getId().equals(shopId)){
                System.out.println("삭제할 메뉴의 매장번호 : " + shop.getId());
                return true;
            }
        }
        return false;
    }

    public ResponseEntity<?> getMyShop(String authorization) {
        System.out.println("ShopController in getMyShop");
        String loginId = userService.getMyId(authorization);
        User userEntity = userRepository.findById(loginId).get();
        List<Shop> result = getShopListByOwnerId(userEntity.getId());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> get(String shopId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getList() {
        return null;
    }

    @Override
    public ResponseEntity<?> post(Shop.Request request) {
        return null;
    }

    @Override
    public ResponseEntity<?> patch(Shop.Request request) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(String shopId) {
        return null;
    }

    // 89있는 매장번호 인지 확인. 없으면 error를 반환하게 된다. ㅂㅂ
    public boolean isPresent(String shopId)  {
        if(shopRepository.findById(shopId).isPresent())return true;
        throw new ShopNotFoundException();
    }

    @Override
    public boolean isEmpty(String id) {
        return false;
    }
}
