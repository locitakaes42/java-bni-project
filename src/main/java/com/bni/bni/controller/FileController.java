package com.bni.bni.controller;

import java.nio.file.*;
import java.io.*;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.*;
import org.springframework.util.StringUtils;

@RestController
@RequestMapping("/api/files")
public class FileController {
    @Value("${file.upload-dir}")
    private String uploadDir;
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String fileUrl = "api/files/" + fileName;

            return ResponseEntity.ok().body(
                    Map.of(
                        "status", 200,   
                        "message", "File uploaded successfully",
                        "fileUrl", fileUrl
                    )
                );
        }
        catch (IOException e) {
            return ResponseEntity.status(500).body(
                Map.of
                    (
                    "status", 500,
                    "message", "Failed to upload file: " + e.getMessage()
                    )
            );
        }
    }

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String fileName) {
        try {
            Path file = Paths.get(uploadDir).resolve(fileName).normalize();
            Resource resource = new UrlResource(file.toUri());
            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            } 
            String contentType = Files.probeContentType(file);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,"inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);

        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
