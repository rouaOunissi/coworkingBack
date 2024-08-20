package com.esprit.coworkingspaceback.roua.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Component
public class FileNamingUtil {

    public String nameFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null) {
            String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
            return UUID.randomUUID().toString() + extension;
        }
        return UUID.randomUUID().toString();
    }
}
