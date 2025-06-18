package com.mars.portal.backend.util;

public class EntropyUtil {
    public static double calculateEntropy(byte[] data) {
        int[] freq = new int[256];
        for (byte b : data) freq[b & 0xFF]++;
        double entropy = 0;
        int length = data.length;
        for (int count : freq) {
            if (count == 0) continue;
            double p = (double) count / length;
            entropy -= p * (Math.log(p) / Math.log(2));
        }
        return entropy; // bits per symbol
    }
}
