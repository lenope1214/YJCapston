package com.jumanji.capston.service;

import com.jumanji.capston.config.StorageConfig;
import com.jumanji.capston.storage.StorageException;
import com.jumanji.capston.storage.StorageFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.NotSupportedException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class StorageService {
    private final Path rootLocation;


    @Autowired
    public StorageService(StorageConfig config) {
//        System.out.println("StorageServicec constructor...");
        this.rootLocation = Paths.get(config.getLocation());
    }

    //
    public String store(MultipartFile file, String fileName, String... filePath) {
        String newFileName = null;
        File dirFile = null;
        String fileExtension;
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }

            fileExtension = Objects.requireNonNull(file.getContentType()).substring(file.getContentType().indexOf('/') + 1);
            System.out.println("파일 확장자 : " + fileExtension);
            if(!(fileExtension.equals("jpg") || fileExtension.equals("png") || fileExtension.equals("jpeg")))throw new NotSupportedException("파일 확장자를 맞춰주세요");

            String path = pathValidation(filePath);


//            System.out.println("file.getName() : " + file.getName());
//            System.out.println("file.getContentType() : " + file.getContentType());
//            System.out.println("file.getContentType().substring by '/' : " + file.getContentType().substring(file.getContentType().indexOf('/')+1));
//            System.out.println("file.getOriginalFilename() : " + file.getOriginalFilename());
//            System.out.println("file.getResource() : " + file.getResource());
//            System.out.println("file.getClass() : " + file.getClass());
            System.out.println("루트 로케이션 : " + rootLocation );
            dirFile = new File(String.valueOf(rootLocation + "\\" + path + "\\" + fileName + "." + fileExtension));
            System.out.println("dirFile.toString() : " + dirFile.toString());
            if(!dirFile.exists())Files.createDirectory(dirFile.toPath());
            File[] fileList = dirFile.listFiles();
            assert fileList != null;
//            System.out.println("경로 내 파일 수 : " + (fileList.length));
            newFileName = (fileList.length+1) + "." + fileExtension;
            System.out.println("파일 경로 :" + newFileName);
            Path destinationFile = Path.of((dirFile +"\\"+ newFileName));
//                    .normalize().toAbsolutePath(); // file.getOriginalFilename() -> filename.filetype 이런 형태
//            Path destinationFile = this.rootLocation.resolve(Paths.get(dirFile +"\\"+ newFileName))
//                    .normalize().toAbsolutePath(); // file.getOriginalFilename() -> filename.filetype 이런 형태
            System.out.println("풀 경로 : " + dirFile +"\\"+ newFileName);
            System.out.println("destinationFile.getParent() : " + destinationFile.getParent());
//            System.out.println("rootLocation's 절대 주소 : " + this.rootLocation.toAbsolutePath());
//            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
//                throw new StorageException("Cannot store file outside current directory.");
//            } // 상위 디렉토리 판별기..

            try (InputStream inputStream = file.getInputStream()) {

                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);

            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        } catch (NotSupportedException e) {
            throw new StorageException("Not supported file extension");
        }
        return dirFile.getPath() + "." + fileExtension;
    }

    public String pathValidation(String... paths) throws IOException {
        System.out.println("pathValidation ... ");
        File dirFile = null;
        String filePath = String.valueOf(rootLocation);
        for(int i=0; i<paths.length-1;i++){
            filePath = filePath + "\\" + paths[i];
            dirFile = new File(filePath);
            System.out.println("now checking path : " + filePath);
            if(!dirFile.exists())Files.createDirectory(dirFile.toPath());
        }
        System.out.println("포문 정상 종료");
        return filePath;
    }

    public Stream<Path> loadAll() {
        try {
            System.out.println("loadAll()'s this.rootLocation : " + this.rootLocation);
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }
    }

    public Path load(String filename, String path) {
        System.out.println("load -> path : " + path);
        return Path.of(path).resolve(filename);
    }

    public Resource loadAsResource(String filename, String path) {
        try {
            Path file = load(filename, path);
            Resource resource = new UrlResource(file.toUri());
//            List<Resource> files;
            System.out.println("loadAsResource's file.toUri() : " + file.toUri());
            System.out.println("Resource is readable ? " + resource.isReadable());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    public Resource loadMenuImg(String shopId, String menuName) {
        try {
            Path file = load("", "");
            Resource resource = new UrlResource(file.toUri());
            System.out.println("loadAsResource's file.toUri() : " + file.toUri());
            System.out.println("Resource is readable ? " + resource.isReadable());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + "");
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + "", e);
        }
    }

    public List<Resource> loadShopThumbNailImg(String shopId) {
        List<Resource> resourceList = null;
//        resourceList = Path.of("")
//        File dirFile = null;
//        dirFile = new File(String.valueOf(rootLocation + "\\shop\\" + shopId + "\\thumbnails" ));
//        if(!dirFile.exists())throw new StorageFileNotFoundException("매장 썸네일이 없습니다.");
//        File[] fileList = dirFile.listFiles();
//        assert fileList != null;
//        System.out.println("경로 내 파일 수 : " + (fileList.length));
        return resourceList;
//        return fileList;
//             List<File> files = Arrays.asList(fileList);
//            return fileList;
    }



    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    public void init() {
        try {
            Files.createDirectories(rootLocation);
//            Files.createDirectories(Path.of(rootLocation + "\\menu"));
            Files.createDirectories(Path.of(rootLocation + "\\shop"));
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}