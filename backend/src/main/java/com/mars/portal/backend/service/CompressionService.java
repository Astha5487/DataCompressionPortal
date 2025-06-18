package com.mars.portal.backend.service;

import com.mars.portal.backend.model.CompressionResult;
import com.mars.portal.backend.util.EntropyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CompressionService {

    private final Map<String, Compressor> compressorMap;

    @Autowired
    public CompressionService(List<Compressor> compressors) {
        this.compressorMap = new HashMap<>();
        for (Compressor c : compressors) {
            String name = c.getClass().getAnnotation(Service.class).value(); // Use @Service("lz77") etc.
            compressorMap.put(name.toLowerCase(), c);
        }
    }

    public CompressionResult compress(MultipartFile multipartFile, String algorithm) throws IOException {
        Compressor compressor = compressorMap.get(algorithm.toLowerCase());
        if (compressor == null) throw new IllegalArgumentException("Unknown algorithm: " + algorithm);

        File input = convertToFile(multipartFile);
        long originalSize = input.length();

        byte[] originalBytes = Files.readAllBytes(input.toPath());
        double entropy = EntropyUtil.calculateEntropy(originalBytes);

        long start = System.currentTimeMillis();
        File compressedFile = compressor.compress(input);
        long end = System.currentTimeMillis();

        long compressedSize = compressedFile.length();
        long timeTaken = end - start;

        double compressionRatio = originalSize == 0 ? 0 : (double) compressedSize / originalSize;
        double speed = timeTaken == 0 ? 0 : (originalSize / 1_000_000.0) / (timeTaken / 1000.0); // MB/s

        String compressedFileName = compressedFile.getName(); // ✅ Fix: Get filename from the File object

        CompressionResult result = new CompressionResult();
        result.setCompressedFile(compressedFile.getAbsolutePath());
        result.setOriginalSize(originalSize);
        result.setCompressedSize(compressedSize);
        result.setProcessingTimeMillis(timeTaken);
        result.setEntropy(entropy);
        result.setCompressionRatio(compressionRatio);
        result.setSpeedMBperSec(speed);
        result.setAlgorithm(algorithm);
        result.setFileName(compressedFileName);

        return result;
    }

    public CompressionResult decompress(MultipartFile multipartFile, String algorithm) throws IOException {
        Compressor compressor = compressorMap.get(algorithm.toLowerCase());
        if (compressor == null) throw new IllegalArgumentException("Unknown algorithm: " + algorithm);

        // Convert uploaded multipart file to a File object
        File input = convertToFile(multipartFile);
        long originalSize = input.length();

        // Decompress to a temporary file
        File tempOutput = compressor.decompress(input);

        // Create uploads directory if it doesn't exist
        File uploadsDir = new File("uploads");
        if (!uploadsDir.exists()) uploadsDir.mkdirs();

        // Create a final decompressed file name and path
        String decompressedFileName = "decompressed_" + multipartFile.getOriginalFilename();
        File finalDecompressedFile = new File(uploadsDir, decompressedFileName);

        // Copy temp decompressed content to uploads directory
        Files.copy(tempOutput.toPath(), finalDecompressedFile.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);

        long decompressedSize = finalDecompressedFile.length();
        long timeTaken = System.currentTimeMillis() - input.lastModified(); // Or use start-end if needed

        CompressionResult result = new CompressionResult();
        result.setCompressedFile(finalDecompressedFile.getAbsolutePath());
        result.setOriginalSize(originalSize);
        result.setCompressedSize(decompressedSize); // this is size after decompression
        result.setProcessingTimeMillis(timeTaken);
        result.setAlgorithm(algorithm);
        result.setFileName(finalDecompressedFile.getName()); // for downloading via endpoint

        return result;
    }




    private File convertToFile(MultipartFile multipartFile) throws IOException {
        File convFile = File.createTempFile("upload_", "_" + multipartFile.getOriginalFilename());
        multipartFile.transferTo(convFile);
        return convFile;
    }
}
