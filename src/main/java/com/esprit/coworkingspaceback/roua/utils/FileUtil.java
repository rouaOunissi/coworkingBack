package com.esprit.coworkingspaceback.roua.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileUtil {

    public void saveNewFile(String uploadDir, String newPhotoName, MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        Path path = Paths.get(uploadDir + newPhotoName);
        Files.write(path, bytes);
    }
}
