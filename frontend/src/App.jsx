import React, { useState } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import Home from "./Pages/Home";
import Statistics from "./Pages/Statistics";
import About from "./Pages/About";




function App() {
  const [results, setResults] = useState([]);

  return (
    <Router>
    <div className="bg-gray-100 dark:bg-gray-900 text-gray-900 dark:text-gray-100 min-h-screen transition-colors duration-300">
        <Navbar />
        <div className="p-4">
          <Routes>
            <Route path="/" element={<Home setResults={setResults} />} />
            <Route path="/statistics" element={<Statistics results={results} />} />
            <Route path="/about" element={<About />} />
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;
