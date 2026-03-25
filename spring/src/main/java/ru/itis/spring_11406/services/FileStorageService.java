package ru.itis.spring_11406.services;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String saveFile(MultipartFile uploadFile);
    void writeFileToResponse(String fileName, HttpServletResponse response);
}
