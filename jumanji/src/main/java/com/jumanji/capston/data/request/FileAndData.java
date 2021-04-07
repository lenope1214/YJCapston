package com.jumanji.capston.data.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileAndData {
    MultipartFile file;
    String code;
}
