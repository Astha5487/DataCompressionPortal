package com.mars.portal.backend.model;

public class CompressionResult {

    private String compressedFile;
    private long originalSize;
    private long compressedSize;
    private long processingTimeMillis;
    private double entropy;
    private double compressionRatio;
    private double speedMBperSec;
    private String algorithm;
    private String fileName;



    public String getCompressedFile() {
        return compressedFile;
    }

    public void setCompressedFile(String compressedFile) {
        this.compressedFile = compressedFile;
    }

    public long getOriginalSize() {
        return originalSize;
    }

    public void setOriginalSize(long originalSize) {
        this.originalSize = originalSize;
    }

    public long getCompressedSize() {
        return compressedSize;
    }

    public void setCompressedSize(long compressedSize) {
        this.compressedSize = compressedSize;
    }

    public long getProcessingTimeMillis() {
        return processingTimeMillis;
    }

    public void setProcessingTimeMillis(long processingTimeMillis) {
        this.processingTimeMillis = processingTimeMillis;
    }

    public double getEntropy() {
        return entropy;
    }

    public void setEntropy(double entropy) {
        this.entropy = entropy;
    }

    public double getCompressionRatio() {
        return compressionRatio;
    }

    public void setCompressionRatio(double compressionRatio) {
        this.compressionRatio = compressionRatio;
    }

    public double getSpeedMBperSec() {
        return speedMBperSec;
    }

    public void setSpeedMBperSec(double speedMBperSec) {
        this.speedMBperSec = speedMBperSec;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
