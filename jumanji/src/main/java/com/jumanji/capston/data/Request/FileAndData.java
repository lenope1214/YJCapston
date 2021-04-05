package com.jumanji.capston.data.Request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileAndData {
    MultipartFile file;
    String code;
}
