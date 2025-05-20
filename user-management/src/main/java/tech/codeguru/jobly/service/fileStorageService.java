package tech.codeguru.jobly.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class fileStorageService {
    public static String uploadFile(MultipartFile file) {
        // Save to file system or cloud (like S3)
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path path = Paths.get("uploads/" + filename);

        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return "/uploads/" + filename; // return accessible URL or path
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }
}
