 Data Compression & Decompression Portal
A full-stack web application that allows users to compress and decompress files using various compression algorithms including Huffman, LZ77, Delta, and RLE. It also provides insightful compression statistics, visual comparisons, and a clean user interface with light/dark mode toggle.

✨ Features
✅ Upload any file and compress or decompress it using selected algorithm
✅ Compression statistics: Original size, Compressed size, Entropy, Compression Ratio, Speed, Processing Time
✅ Algorithm comparison charts using bar graphs
✅ Smart warning if compression is not effective (e.g. for random or high-entropy data)
✅ Responsive UI with dark/light mode toggle
✅ Easy download of the processed file
✅ Built using modern web technologies

🧪 Steps to Use the Portal
Navigate to the Upload Section

Click on “Choose File” to upload a text, image, or binary file.

Select your preferred compression algorithm from the dropdown:

Huffman

LZ77

RLE

Delta

Choose an Action

Click Compress File to apply the selected compression algorithm.

Click Decompress File if your uploaded file is already compressed.

View Compression Statistics

After processing, navigate to the Statistics section.

You'll see a comparison chart of:

📉 Compression Ratio

⚡ Speed (MB/s)

🧠 Entropy

The stats help you evaluate algorithm performance on your file.

Download Result

A link is provided to download the compressed or decompressed file.

Understand the Project

Visit the About section to read the project overview and tech stack used.

🛠️ Tech Stack
Frontend	Backend	Styling	Charts
React	Spring Boot	Tailwind CSS	Recharts

🧠 Compression Algorithms Supported
Huffman Coding – Tree-based entropy encoding

LZ77 – Sliding window dictionary compression

Delta Encoding – Efficient for numeric sequences

RLE (Run-Length Encoding) – Best for repeating data

📊 Sample Output
📈 Compression Result
Algorithm: Huffman
Original Size: 1000 bytes
Compressed Size: 6314 bytes
Compression Ratio: 6.31
Entropy: 5.893
Speed: 0.50 MB/s
Processing Time: 2 ms
⚠️ Warning: File has high entropy — compression may increase size.
