package com.mars.portal.backend.dto;

public class CompressionResultDTO {
    private String fileName;
    private long originalSize;
    private long compressedSize;
    private double compressionRatio;
    private long processingTimeMillis;

    // Constructors
    public CompressionResultDTO(String fileName, long originalSize, long compressedSize, double compressionRatio, long processingTimeMillis) {
        this.fileName = fileName;
        this.originalSize = originalSize;
        this.compressedSize = compressedSize;
        this.compressionRatio = compressionRatio;
        this.processingTimeMillis = processingTimeMillis;
    }

}
