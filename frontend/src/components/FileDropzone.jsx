import React, { useState } from "react";
import axios from "axios";
import { API_BASE_URL } from "../config/api";

const FileDropzone = ({ setResults }) => {
  const [selectedFile, setSelectedFile] = useState(null);
  const [selectedAlgorithm, setSelectedAlgorithm] = useState("huffman");
  const [mode, setMode] = useState("compress"); // "compress" or "decompress"
  const [stats, setStats] = useState(null);
  const [downloadFileName, setDownloadFileName] = useState(null);

  const handleFileChange = (e) => {
    setSelectedFile(e.target.files[0]);
    setStats(null);
    setDownloadFileName(null);
  };

  const handleProcess = async () => {
    if (!selectedFile) {
      alert("Please select a file first.");
      return;
    }

    const formData = new FormData();
    formData.append("file", selectedFile);
    formData.append("algorithm", selectedAlgorithm);

    try {
      const endpoint = mode === "compress" ? "compress" : "decompress";
      const response = await axios.post(`${API_BASE_URL}/${endpoint}`, formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      });
      
      const {
        fileName,
        originalSize,
        compressedSize,
        entropy,
        processingTimeMillis,
        compressionRatio,
        speedMBperSec,
        algorithm,
      } = response.data;

      const result = {
        algorithm,
        originalSize,
        compressedSize,
        entropy,
        processingTimeMillis,
        compressionRatio,
        speedMBperSec,
        mode,
      };

      setStats(result);
      setResults((prev) => [...prev, result]);
      setDownloadFileName(fileName);
    } catch (error) {
      console.error(`${mode} failed:`, error);
      alert(`${mode === "compress" ? "Compression" : "Decompression"} failed. Please try again.`);
    }
  };

  return (
    <div className="p-4 border rounded-md shadow-md bg-white dark:bg-gray-900 dark:border-gray-700 text-gray-900 dark:text-gray-100">
      <h2 className="text-xl font-semibold mb-2 text-gray-800 dark:text-white">Upload a File</h2>

      {/* Mode Toggle */}
      <div className="mb-4 flex space-x-4">
        <button
          onClick={() => setMode("compress")}
          className={`px-4 py-2 rounded ${
            mode === "compress" ? "bg-blue-600 text-white" : "bg-gray-200 dark:bg-gray-700"
          }`}
        >
          Compress
        </button>
        <button
          onClick={() => setMode("decompress")}
          className={`px-4 py-2 rounded ${
            mode === "decompress" ? "bg-blue-600 text-white" : "bg-gray-200 dark:bg-gray-700"
          }`}
        >
          Decompress
        </button>
      </div>

      <input type="file" onChange={handleFileChange} className="mb-2" />

      <select
  value={selectedAlgorithm}
  onChange={(e) => setSelectedAlgorithm(e.target.value)}
  className="mb-4 block border border-gray-300 rounded p-2 bg-white text-gray-900 dark:bg-gray-800 dark:text-white"
>
  <option className="text-black dark:text-white bg-white dark:bg-gray-800" value="huffman">Huffman</option>
  <option className="text-black dark:text-white bg-white dark:bg-gray-800" value="lz77">LZ77</option>
  <option className="text-black dark:text-white bg-white dark:bg-gray-800" value="delta">Delta</option>
  <option className="text-black dark:text-white bg-white dark:bg-gray-800" value="rle">RLE</option>
</select>


      <div className="flex items-center gap-4 mt-4">
        <button
          className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded"
          onClick={handleProcess}
        >
          {mode === "compress" ? "Compress File" : "Decompress File"}
        </button>

        {downloadFileName && (
          <a
            href={`${API_BASE_URL}/download/${downloadFileName}`}
            download
            className="bg-green-500 hover:bg-green-600 text-white px-4 py-2 rounded"
          >
            Download {mode === "compress" ? "Compressed" : "Decompressed"} File
          </a>
        )}
      </div>

      {stats && (
  <div className="bg-gray-100 dark:bg-gray-800 p-4 rounded shadow mt-4 text-gray-900 dark:text-gray-100">
    <h3 className="font-semibold mb-2">
      📈 {mode === "compress" ? "Compression" : "Decompression"} Result
    </h3>

    <p><strong>Algorithm:</strong> {stats.algorithm}</p>
    <p><strong>Original Size:</strong> {stats.originalSize} bytes</p>
    <p><strong>{mode === "compress" ? "Compressed" : "Decompressed"} Size:</strong> {stats.compressedSize} bytes</p>

    {/* ⚠️ Show Warning If Compressed Size Is Larger */}
    {mode === "compress" && stats.compressedSize > stats.originalSize && (
      <div className="bg-yellow-100 text-yellow-800 border border-yellow-300 rounded-md p-3 my-4 dark:bg-yellow-200 dark:text-yellow-900">
        ⚠️ <strong>Note:</strong> Compression increased the file size.
        This usually happens with <span className="font-semibold">random or already compressed files</span>.
      </div>
    )}

    {mode === "compress" && (
      <>
        <p><strong>Compression Ratio:</strong> {stats.compressionRatio?.toFixed(2)}</p>
        <p><strong>Entropy:</strong> {stats.entropy?.toFixed(3)}</p>
        <p><strong>Speed:</strong> {stats.speedMBperSec?.toFixed(2)} MB/s</p>
      </>
    )}

    <p><strong>Processing Time:</strong> {stats.processingTimeMillis} ms</p>
  </div>
)}

    </div>
  );
};

export default FileDropzone;
