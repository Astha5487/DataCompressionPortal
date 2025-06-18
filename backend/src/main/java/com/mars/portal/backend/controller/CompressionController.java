package com.mars.portal.backend.controller;

import com.mars.portal.backend.model.CompressionResult;
import com.mars.portal.backend.service.CompressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class CompressionController {

    @Autowired
    private CompressionService compressionService;

    @PostMapping("/compress")
    public ResponseEntity<CompressionResult> compressFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("algorithm") String algorithm) {

        try {
            CompressionResult result = compressionService.compress(file, algorithm);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/decompress")
    public ResponseEntity<CompressionResult> decompressFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("algorithm") String algorithm) {
        try {
            CompressionResult result = compressionService.decompress(file, algorithm);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        try {
            Path filePath = Paths.get("compressed_files").resolve(filename).normalize(); // your compressed output dir
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
