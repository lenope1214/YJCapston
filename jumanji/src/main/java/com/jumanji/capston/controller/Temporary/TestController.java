package com.jumanji.capston.controller.Temporary;

import com.jumanji.capston.data.Menu;
import com.jumanji.capston.data.Request.FileAndData;
import com.jumanji.capston.data.Request.MenuRequest;
import com.jumanji.capston.service.MenuService;
import com.jumanji.capston.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {
    @Autowired
    MenuService menuService;
    @Autowired
    StorageService storageService;
    @Autowired
    HttpHeaders httpHeaders;
//    @Autowired
//    NaverPlaceSearchService naverPlaceSearchService;
//
//    //    @GetMapping("/addressApiTest/{keyword}")
////    public String callAddressApi(@PathVariable String keyword) {
////        System.out.println("Keyword : " + keyword);
////        return naverPlaceSearchService.searchPlace(keyword);
////    }



    @Transactional
    @PostMapping("/menu") // multipart/form-data 형식은 param으로 parsing 해준다는데.,..?
    public ResponseEntity<?> insertMenu(MenuRequest menuRequest) {
        System.out.println("메뉴 테스팅중..!");
        Menu menu;
        MultipartFile file = menuRequest.getImg();
        System.out.println("file이 제대로 왔는지 확인 시작==================================");
        System.out.println("file.getOriginalFilename : " + file.getOriginalFilename());
        System.out.println("file.getResource : " + file.getResource());
        System.out.println("file.getContentType : " + file.getContentType());
        System.out.println("file.getName : " + file.getName());
        System.out.println("file.getSize : " + file.getSize());
        System.out.println("============================================================");
        String fileUrl = storageService.store(file, menuRequest.getShopId(), "menu");
        BigDecimal menuSeq = menuService.getMenuSeqNextVal();
//        System.out.println("seq : " + menuSeq);
        menu = Menu.createMenu()
                .id(menuRequest.getShopId() + "" + menuSeq)
                .name(menuRequest.getName())
                .intro(menuRequest.getIntro())
                .price(menuRequest.getPrice())
                .duration(menuRequest.getDuration())
                .imgUrl(fileUrl)
                .build();
        Object result = menuService.insert(menu);
        if (result.getClass() != Menu.class)
            return new ResponseEntity<>("저장 실패", httpHeaders, HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping("/uploadTest01")
    public ResponseEntity<?> uploadTest01(MultipartFile multipartFile){
        String fileUrl = storageService.store(multipartFile, "testCode", "test");
        if(fileUrl == null)
            return new ResponseEntity<>("파일이 안온듯?", httpHeaders, HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(fileUrl, HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping("/uploadTest02")
    public ResponseEntity<?> uploadTest02(FileAndData fileAndData){
        String fileUrl = storageService.store(fileAndData.getFile(), fileAndData.getCode(), "test");
        if(fileUrl == null)
            return new ResponseEntity<>("파일이 안온듯?", httpHeaders, HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(fileUrl, HttpStatus.CREATED);
    }
}
