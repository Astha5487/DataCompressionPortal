package com.mars.portal.backend.service.Impl;

import com.mars.portal.backend.service.Compressor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

@Service("huffman")
public class HuffmanCompressor implements Compressor {

    // Node for Huffman Tree
    private static class Node {
        char ch;
        int freq;
        Node left, right;

        Node(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }

        Node(int freq, Node left, Node right) {
            this.ch = '\0'; // internal node
            this.freq = freq;
            this.left = left;
            this.right = right;
        }
    }

    private final Map<Character, String> huffmanCodes = new HashMap<>();
    private final Map<String, Character> reverseCodes = new HashMap<>();

    @Override
    public File compress(File inputFile) throws IOException {
        String text = new String(Files.readAllBytes(inputFile.toPath()));
        Map<Character, Integer> freqMap = new HashMap<>();

        for (char c : text.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.freq));
        for (var entry : freqMap.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }

        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            pq.add(new Node(left.freq + right.freq, left, right));
        }

        Node root = pq.peek();
        buildCodeMap(root, "");

        StringBuilder encoded = new StringBuilder();
        for (char c : text.toCharArray()) {
            encoded.append(huffmanCodes.get(c));
        }


        File outputDir = new File("compressed_files");
        outputDir.mkdirs();
        File output = new File(outputDir, "compressed_huffman.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
            writer.write(freqMap.size() + "\n");
            for (var entry : freqMap.entrySet()) {
                writer.write((int) entry.getKey() + " " + entry.getValue() + "\n");
            }
            writer.write(encoded.toString());
        }

        return output;
    }


    @Override
    public File decompress(File compressedFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(compressedFile));
        int size = Integer.parseInt(reader.readLine());

        Map<Character, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < size; i++) {
            String[] parts = reader.readLine().split(" ");
            char ch = (char) Integer.parseInt(parts[0]);
            int freq = Integer.parseInt(parts[1]);
            freqMap.put(ch, freq);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.freq));
        for (var entry : freqMap.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }

        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            pq.add(new Node(left.freq + right.freq, left, right));
        }

        Node root = pq.peek();
        buildReverseCodeMap(root, "");

        // Read encoded data
        StringBuilder encoded = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            encoded.append(line);
        }
        reader.close();

        // Decode
        File output = new File("decompressed_huffman.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
            Node node = root;
            for (char bit : encoded.toString().toCharArray()) {
                node = (bit == '0') ? node.left : node.right;
                if (node.left == null && node.right == null) {
                    writer.write(node.ch);
                    node = root;
                }
            }
        }

        return output;
    }

    private void buildCodeMap(Node node, String code) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            huffmanCodes.put(node.ch, code);
            return;
        }
        buildCodeMap(node.left, code + "0");
        buildCodeMap(node.right, code + "1");
    }

    private void buildReverseCodeMap(Node node, String code) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            reverseCodes.put(code, node.ch);
            return;
        }
        buildReverseCodeMap(node.left, code + "0");
        buildReverseCodeMap(node.right, code + "1");
    }
}
