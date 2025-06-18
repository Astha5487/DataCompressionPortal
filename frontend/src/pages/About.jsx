import React from "react";

const About = () => {
  return (
    <div className="text-gray-800 dark:text-gray-100 bg-white dark:bg-gray-900 p-6 rounded-lg shadow-md max-w-4xl mx-auto mt-10">
      <h2 className="text-3xl font-bold mb-6 text-center">📘 About the Project</h2>

      <p className="mb-4">
        The <strong>Data Compression & Decompression Portal</strong> is a full-stack web application designed to efficiently compress and decompress files using advanced algorithms like Huffman Encoding, Delta Encoding, and LZ77 combined with RLE.
      </p>

      <h3 className="text-2xl font-semibold mt-6 mb-2"> Key Features</h3>
      <ul className="list-disc list-inside mb-4 space-y-1">
        <li>Supports multiple compression algorithms (Huffman, Delta, LZ77+RLE).</li>
        <li>Displays compression ratio, entropy, original/compressed size, and time taken.</li>
        <li>Dark/light theme toggle for better user experience.</li>
        <li>Modern responsive UI built with React and Tailwind CSS.</li>
        <li>Spring Boot backend handling compression logic and APIs.</li>
      </ul>

      <h3 className="text-2xl font-semibold mt-6 mb-2"> Tech Stack</h3>
      <ul className="list-disc list-inside mb-4 space-y-1">
        <li><strong>Frontend:</strong> React, Tailwind CSS, Vite</li>
        <li><strong>Backend:</strong> Spring Boot (Java)</li>
        <li><strong>Compression:</strong> Custom implementations of Huffman, Delta, and LZ77+RLE</li>
      </ul>

      <h3 className="text-2xl font-semibold mt-6 mb-2"> Algorithms Implemented</h3>
      <ul className="list-disc list-inside mb-4 space-y-1">
        <li><strong>Huffman Coding:</strong> Entropy encoding using binary trees.</li>
        <li><strong>Delta Encoding:</strong> Stores differences between successive values.</li>
        <li><strong>LZ77 + RLE:</strong> Combines dictionary-based sliding window compression with run-length encoding.</li>
      </ul>

      <h3 className="text-2xl font-semibold mt-6 mb-2"> Future Enhancements</h3>
      <ul className="list-disc list-inside mb-4 space-y-1">
        <li>Visual performance graphs comparing all algorithms.</li>
        <li>AI-based algorithm recommendation system based on file characteristics.</li>
        <li>Progress bar and status indicator for large file uploads.</li>
        <li>Support for additional file types (images, audio, etc.).</li>
      </ul>

      <h3 className="text-2xl font-semibold mt-6 mb-2"> Creator</h3>
      <p>
        Built by <strong>Astha Jaiswal</strong>, a B.Tech student at <strong>IIT Roorkee</strong>, passionate about backend development, system design, and building impactful tech solutions.
      </p>
    </div>
  );
};

export default About;
