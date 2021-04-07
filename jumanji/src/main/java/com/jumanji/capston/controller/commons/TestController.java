package com.jumanji.capston.controller.commons;

import com.jumanji.capston.controller.exception.ApiErrorResponse;
import com.jumanji.capston.data.request.FileAndData;
import com.jumanji.capston.service.MenuService;
import com.jumanji.capston.service.StorageService;
import com.jumanji.capston.storage.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.http.HttpResponse;

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


//    @Transactional
//    @PostMapping("/menu") // multipart/form-data 형식은 param으로 parsing 해준다는데.,..?
//    public ResponseEntity<?> insertMenu(MenuRequest menuRequest) {
//        System.out.println("메뉴 테스팅중..!");
//        Menu menu;
//        MultipartFile file = menuRequest.getImg();
//        System.out.println("file이 제대로 왔는지 확인 시작==================================");
//        System.out.println("file.getOriginalFilename : " + file.getOriginalFilename());
//        System.out.println("file.getResource : " + file.getResource());
//        System.out.println("file.getContentType : " + file.getContentType());
//        System.out.println("file.getName : " + file.getName());
//        System.out.println("file.getSize : " + file.getSize());
//        System.out.println("============================================================");
//        String fileUrl = storageService.store(file, menuRequest.getShopId(), "menu");
//        BigDecimal menuSeq = menuService.getMenuSeqNextVal();
//        System.out.println("seq : " + menuSeq);
//        menu = Menu.info()
//                .id(menuRequest.getShopId() + "" + menuSeq)
//                .name(menuRequest.getName())
//                .intro(menuRequest.getIntro())
//                .price(menuRequest.getPrice())
//                .duration(menuRequest.getDuration())
////                .imgUrl(fileUrl)
//                .build();
//        Object result =menuService.save(menu);
//        if (result.getClass() != Menu.class)
//            return new ResponseEntity<>("저장 실패", httpHeaders, HttpStatus.BAD_REQUEST);
//        else
//            return new ResponseEntity<>(result, HttpStatus.CREATED);
//    }

//    @GetMapping("/loadImg/{filename}")
//    public ResponseEntity<?> loadFileTest(@PathVariable String filename, HttpServletRequest request) {
//        Resource file = storageService.loadAsResource(filename);
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE,
//                "filename=" + file.getFilename()).body(file);
////        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
////                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
//    }

    @Transactional
    @PostMapping("/uploadTest01")
    public ResponseEntity<?> uploadTest01(MultipartFile multipartFile, HttpResponse response) {
        try {
            System.out.println("저장 전.");
            storageService.store(multipartFile, "testCode", "test");
            System.out.println("저장 후.");
        } catch (StorageException e) {
            return new ResponseEntity<>(new ApiErrorResponse("error-9000", e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(multipartFile, HttpStatus.CREATED);
        //        String fileUrl = storageService.store(multipartFile, "testCode", "test");
//        if(fileUrl == null)
//            return new ResponseEntity<>("파일이 안온듯?", httpHeaders, HttpStatus.BAD_REQUEST);
//        else
//            return new ResponseEntity<>(fileUrl, HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping("/uploadTest02") // api/v1/test/uploadTest02
    public ResponseEntity<?> uploadTest02(FileAndData fileAndData) {
        String fileUrl = storageService.store(fileAndData.getFile(), fileAndData.getCode(), "test");
        if (fileUrl == null)
            return new ResponseEntity<>("파일이 안온듯?", httpHeaders, HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(fileUrl, HttpStatus.CREATED);
    }



//    @GetMapping("/loadImg/{fileName}")
//    public ResponseEntity<?> loadFile(@PathVariable String fileName) throws ParseException {
//        return new ResponseEntity<>(storageService.loadAsResource(fileName), HttpStatus.OK);
//    }
//
//    @GetMapping("/loadImg2/{filename:.+}")
//    @ResponseBody
//    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
//        Resource file = storageService.loadAsResource(filename);
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE,
////                "image/jpeg; filename=\"" + file.getFilename() + "\"").body(file);
////                "image/jpeg;Content-Disposition: inline; filename=\"" + file.getFilename() + "\"").body(file);
//        "application/octet-stream;Content-Disposition: inline; filename=\"" + file.getFilename() + "\"").body(file);
//    }

//    @GetMapping("/loadImg3/{filename:.+}")
//    @ResponseBody
//    public ResponseEntity<?> serveFile2(@PathVariable String filename) {
//        Resource file = storageService.loadAsResource(filename, "src\\main\\resources\\uploads\\shop\\0101010107");
//        Shop.Response response = new Shop.Response();
//        response.setImg(file);
//        response.setIntro("테스트");
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE,
////                "image/jpeg; filename=\"" + file.getFilename() + "\"").body(file);
////                "image/jpeg;Content-Disposition: inline; filename=\"" + file.getFilename() + "\"").body(response);
//                "image/jpeg; filename=\"" + file.getFilename() + "\"").body(response.getImg());
//    }

//    @GetMapping("/loadTest1/{filename:.+}")
//    @ResponseBody
//    public ResponseEntity<Resource> loadTest1(Shop.Request reqeust) {
//        Resource file = storageService.loadAsResource(filename);
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE,
//                "image/jpeg; filename=\"" + file.getFilename() + "\"").body(file);
//    }

}