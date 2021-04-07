package com.jumanji.capston.controller.commons;

import com.jumanji.capston.service.StorageService;
import com.jumanji.capston.storage.StorageFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/files")
public class FileController {
    private final StorageService storageService;
    @Autowired
    public FileController(StorageService storageService){
        this.storageService = storageService;
    }

    @GetMapping(value={"", "/"})
    public String files(Model model) throws IOException {
        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));
        return "uploadForm";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

//    @GetMapping("/{filename:.+}")
//    @ResponseBody
//    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
//
//        Resource file = storageService.loadAsResource(filename);
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
//    } // 파일 다운로드 경로

    @GetMapping("/shop/{shopId}/menu/{fileName:.+}")
    @ResponseBody
    public ResponseEntity<?> loadImg(@PathVariable String shopId, @PathVariable String fileName) {
        System.out.println("이미지 로드 요청 !!!");
        Resource img = storageService.loadImg("files", "shop", shopId, "menu", fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE,
                "image/jpeg; filename=\"" + img.getFilename() + "\"").body(img);
    }

    @GetMapping("/shop/{shopId}/thumbnail/{fileName:.+}")
    @ResponseBody
    public ResponseEntity<?> loadThumbNail(@PathVariable String shopId, @PathVariable String fileName) {
        System.out.println("이미지 로드 요청 !!!");
        Resource img = storageService.loadImg("files", "shop", shopId, "thumbNail", fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE,
                "image/jpeg; filename=\"" + img.getFilename() + "\"").body(img);
    }

    // 파일 불러오기
//    @GetMapping("/loadImg/{shopId}")
//    @ResponseBody
//    public ResponseEntity<?> loadImgbyShopId(  @PathVariable String shopId  ) {
//        List<Resource> file = storageService.loadShopThumbNailImg(shopId);
//        List<Resource> file = storageService.loadShopThumbNailImg(shopId);
//        return new ResponseEntity<>(file, HttpStatus.OK);
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE,
//                "image/jpeg; filename=\"" + file.getFilename() + "\"").body(file);
//        return file;
//    }

//    @GetMapping("/loadImg/{shopId}/{menuName}")
//    @ResponseBody
//    public ResponseEntity<?> loadImg(
//            @PathVariable String shopId,
//            @PathVariable String menuName ) {
//        Resource file = storageService.loadMenuImg(shopId, menuName);
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE,
//                "image/jpeg; filename=\"" + file.getFilename() + "\"").body(file);
//    }

    @PostMapping(value={"", "/"})
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        storageService.store(file, "022344278\\test", "파일이름");
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/files";
    }

    @GetMapping("/pathValidationTest")
    public String pathValidationTest(@RequestBody String path){
        try {
            storageService.pathValidation(path.split("/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("컨트롤러 정상 종료");
        return "redirect:/";
    }
}
