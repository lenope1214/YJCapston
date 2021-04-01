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

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
    public String store(MultipartFile file, String idCode, String typeName) {
        String fileUrl = null;
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            String fileExtension = Objects.requireNonNull(file.getContentType()).substring(file.getContentType().indexOf('/') + 1);

//            System.out.println("file.getName() : " + file.getName());
//            System.out.println("file.getContentType() : " + file.getContentType());
//            System.out.println("file.getContentType().substring by '/' : " + file.getContentType().substring(file.getContentType().indexOf('/')+1));
//            System.out.println("file.getOriginalFilename() : " + file.getOriginalFilename());
//            System.out.println("file.getResource() : " + file.getResource());
//            System.out.println("file.getClass() : " + file.getClass());

            Path destinationFile = this.rootLocation.resolve(Paths.get( typeName+ idCode + "." + fileExtension))
                    .normalize().toAbsolutePath(); // file.getOriginalFilename() -> filename.filetype 이런 형태
//            System.out.println("destinationFile.getParent() : " + destinationFile.getParent());
//            System.out.println("rootLocation's 절대 주소 : " + this.rootLocation.toAbsolutePath());
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new StorageException("Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
        return rootLocation.getParent().toString();
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

    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
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

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}