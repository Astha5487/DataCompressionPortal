import React from "react";
import FileDropzone from "../components/FileDropzone";

const Home = ({ setResults }) => {
  return (
    <div className="max-w-5xl mx-auto py-8 px-4 bg-white dark:bg-gray-900 rounded-lg shadow">
      <h1 className="text-2xl font-bold mb-4">📂 Data Compression Portal</h1>
      <FileDropzone setResults={setResults} />
    </div>
  );
};

export default Home;
