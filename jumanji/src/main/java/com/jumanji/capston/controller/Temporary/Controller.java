package com.jumanji.capston.controller.Temporary;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class Controller {
//    @Autowired
//    StorageService storageService;
//
//    @Autowired
//    ShopService shopService;
//
//    @Autowired
//    HttpHeaders httpHeaders;
//
//
//    @GetMapping("/test/dateFormat")
//    public ResponseEntity<?> testDateFormat() throws ParseException {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
//        Date tempDate = dateFormat.parse("11:00");
//        String strDate = dateFormat.format(tempDate);
//        return new ResponseEntity<>(strDate, HttpStatus.OK);
//    }
//
//    @GetMapping("/test/file")
//    public String files(){
//        return "음..";
//    }
//
//    @PostMapping(value={"/uploadFile"})
//    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
//        String id = SecurityContextHolder.getContext().getAuthentication().getName();
//        if(id == null)new ResponseEntity<>("로그인 해야합니다.", httpHeaders, HttpStatus.BAD_REQUEST);
//        Shop shop = shopService.findById(id);
//        if(shop == null)new ResponseEntity<>("매장이 없습니다.", httpHeaders, HttpStatus.BAD_REQUEST);
//        String shopId = shop.getId();
//        if(shopId == null) new ResponseEntity<>("매장번호(사업자번호)가 잘못됐습니다.", httpHeaders, HttpStatus.BAD_REQUEST);
//        storageService.store(file, shopId);
//
//
//        return new ResponseEntity<>("저장에 성공!", httpHeaders, HttpStatus.CREATED);
////        redirectAttributes.addFlashAttribute("message",
////                "You successfully uploaded " + file.getOriginalFilename() + "!");
////        return "redirect:/files";
//    }
//
//    @ExceptionHandler(StorageFileNotFoundException.class)
//    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
//        return ResponseEntity.notFound().build();
//    }
}