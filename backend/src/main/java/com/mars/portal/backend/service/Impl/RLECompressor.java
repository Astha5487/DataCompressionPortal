package com.mars.portal.backend.service.Impl;

import com.mars.portal.backend.service.Compressor;
import org.springframework.stereotype.Service;

import java.io.*;

@Service("rle")
public class RLECompressor implements Compressor {

    @Override
    public File compress(File inputFile) throws IOException {
        StringBuilder inputText = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                inputText.append(line).append("\n");
            }
        }

        StringBuilder encoded = new StringBuilder();
        String text = inputText.toString();

        int i = 0;
        while (i < text.length()) {
            char current = text.charAt(i);
            int count = 1;
            while (i + 1 < text.length() && text.charAt(i + 1) == current) {
                count++;
                i++;
            }
            encoded.append(count).append(current);
            i++;
        }


        File outputDir = new File("compressed_files");
        outputDir.mkdirs();
        File outputFile = new File(outputDir, "compressed_rle.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(encoded.toString());
        }

        return outputFile;
    }


    @Override
    public File decompress(File compressedFile) throws IOException {
        StringBuilder inputText = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(compressedFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                inputText.append(line);
            }
        }

        StringBuilder decoded = new StringBuilder();
        String text = inputText.toString();

        int i = 0;
        while (i < text.length()) {
            StringBuilder countStr = new StringBuilder();
            while (i < text.length() && Character.isDigit(text.charAt(i))) {
                countStr.append(text.charAt(i));
                i++;
            }
            if (i < text.length()) {
                int count = Integer.parseInt(countStr.toString());
                char ch = text.charAt(i);
                decoded.append(String.valueOf(ch).repeat(count));
                i++;
            }
        }

        File outputFile = new File("decompressed_rle.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(decoded.toString());
        }

        return outputFile;
    }
}
