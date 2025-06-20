# 📦 Data Compression & Decompression Portal

A full-stack web application that allows users to **compress** and **decompress files** using various compression algorithms including **Huffman**, **LZ77**, **Delta**, and **RLE**. It also provides insightful compression statistics, visual comparisons, and a clean user interface with light/dark mode toggle.

---

## ✨ Features

- ✅ Upload any file and compress or decompress it using selected algorithm  
- ✅ Compression statistics: Original size, Compressed size, Entropy, Compression Ratio, Speed, Processing Time  
- ✅ Algorithm comparison charts using bar graphs  
- ✅ Smart warning if compression is not effective (e.g. for random or high-entropy data)  
- ✅ Responsive UI with dark/light mode toggle  
- ✅ Easy download of the processed file  
- ✅ Built using modern web technologies

---

## 🧠 Compression Algorithms Supported

- **Huffman Coding** – Tree-based entropy encoding  
- **LZ77** – Sliding window dictionary compression  
- **Delta Encoding** – Efficient for numeric sequences  
- **RLE (Run-Length Encoding)** – Best for repeating data

---

## 🖥️ Tech Stack

| Frontend | Backend     | Styling      | Charts     |
|----------|-------------|--------------|------------|
| React    | Spring Boot | Tailwind CSS | Recharts   |

---

## 🚀 How to Use

### 🔼 Upload Section

1. Click on **"Choose File"** to upload any file (text, image, binary).
2. Select a **compression algorithm** from the dropdown:
   - Huffman Coding
   - LZ77
   - RLE
   - Delta Encoding
3. Choose between **Compress** or **Decompress** based on your requirement.
4. Click the button to process the file.
5. Once done, download the compressed or decompressed file using the provided link.

---

### 📊 Statistics Section

After compression, you will see:

- 📏 **Original Size** vs. **Compressed Size**
- 📉 **Compression Ratio**
- 🔄 **Processing Time**
- ⚙️ **Speed**
- 🔢 **Entropy**
- 📊 **Visual comparison** of different algorithms in bar charts

If the file has high entropy or is not suitable for compression, a warning is shown.

---

### ℹ️ About Section

This portal is a full-stack web application designed to demonstrate the principles of file compression and decompression using a variety of popular algorithms. It aims to educate users on how different techniques perform across various types of data by providing real-time compression statistics and visual comparisons.
The project highlights the strengths and limitations of each algorithm (like Huffman, LZ77, RLE, and Delta) and helps users understand which algorithm is most suitable for a given file type or data pattern. With a clean UI and detailed feedback, it's both a learning tool and a practical utility for handling file size optimization.
---

## 📸 Sample Output

Algorithm: Huffman
Original Size: 1000 bytes
Compressed Size: 6314 bytes
Compression Ratio: 6.31
Entropy: 5.893
Speed: 0.50 MB/s
Processing Time: 2 ms
⚠️ Warning: File has high entropy — compression may increase size.
