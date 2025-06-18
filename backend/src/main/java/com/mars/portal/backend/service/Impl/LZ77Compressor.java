package com.mars.portal.backend.service.Impl;

import com.mars.portal.backend.service.Compressor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

@Service("lz77")
public class LZ77Compressor implements Compressor {

    private static final int WINDOW_SIZE = 2048;
    private static final int BUFFER_SIZE = 32;

    static class Token {
        int offset, length;
        char nextChar;

        public Token(int offset, int length, char nextChar) {
            this.offset = offset;
            this.length = length;
            this.nextChar = nextChar;
        }

        @Override
        public String toString() {
            return offset + "," + length + "," + nextChar;
        }

        public static Token fromString(String s) {
            String[] parts = s.split(",");
            return new Token(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), parts[2].charAt(0));
        }
    }

    @Override
    public File compress(File inputFile) throws IOException {
        String text = new String(Files.readAllBytes(inputFile.toPath()));
        List<Token> tokens = new ArrayList<>();

        int cursor = 0;
        while (cursor < text.length()) {
            int matchLength = 0;
            int matchOffset = 0;

            int startWindow = Math.max(0, cursor - WINDOW_SIZE);
            String window = text.substring(startWindow, cursor);
            int maxLength = Math.min(BUFFER_SIZE, text.length() - cursor);

            for (int len = 1; len <= maxLength; len++) {
                String substring = text.substring(cursor, cursor + len);
                int index = window.lastIndexOf(substring);
                if (index != -1) {
                    matchOffset = cursor - (startWindow + index);
                    matchLength = len;
                } else {
                    break;
                }
            }

            char nextChar = (cursor + matchLength < text.length()) ? text.charAt(cursor + matchLength) : '\0';
            tokens.add(new Token(matchOffset, matchLength, nextChar));
            cursor += matchLength + 1;
        }

        // ✅ Save to compressed_files
        File outputDir = new File("compressed_files");
        outputDir.mkdirs();
        File output = new File(outputDir, "compressed_lz77.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
            for (Token t : tokens) {
                writer.write(t.toString());
                writer.newLine();
            }
        }

        return output;
    }

    @Override
    public File decompress(File compressedFile) throws IOException {
        List<Token> tokens = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(compressedFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tokens.add(Token.fromString(line));
            }
        }

        StringBuilder outputText = new StringBuilder();

        for (Token token : tokens) {
            int start = outputText.length() - token.offset;
            for (int i = 0; i < token.length; i++) {
                outputText.append(outputText.charAt(start + i));
            }
            if (token.nextChar != '\0') {
                outputText.append(token.nextChar);
            }
        }

        File output = new File("decompressed_lz77.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
            writer.write(outputText.toString());
        }

        return output;
    }
}
