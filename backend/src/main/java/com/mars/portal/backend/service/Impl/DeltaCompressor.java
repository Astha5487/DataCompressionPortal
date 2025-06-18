package com.mars.portal.backend.service.Impl;

import com.mars.portal.backend.service.Compressor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service("delta")
public class DeltaCompressor implements Compressor {

    @Override
    public File compress(File inputFile) throws IOException {
        List<Integer> deltas = new ArrayList<>();


        File outputDir = new File("compressed_files");
        outputDir.mkdirs();
        File compressedFile = new File(outputDir, "compressed_delta.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(compressedFile))) {

            String line;
            int prev = 0;

            while ((line = reader.readLine()) != null) {
                try {
                    int current = Integer.parseInt(line.trim());
                    int delta = current - prev;
                    writer.write(delta + "\n");
                    prev = current;
                } catch (NumberFormatException ignored) {
                }
            }
        }

        return compressedFile;
    }


    @Override
    public File decompress(File compressedFile) throws IOException {
        List<Integer> values = new ArrayList<>();
        File decompressedFile = new File("decompressed_delta.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(compressedFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(decompressedFile))) {

            String line;
            int prev = 0;

            while ((line = reader.readLine()) != null) {
                try {
                    int delta = Integer.parseInt(line.trim());
                    int current = prev + delta;
                    writer.write(current + "\n");
                    prev = current;
                } catch (NumberFormatException ignored) {
                }
            }
        }

        return decompressedFile;
    }
}
