import React from "react";
import { Link, useLocation } from "react-router-dom";
import ThemeToggle from "../components/ThemeToggle";

const Navbar = () => {
  const location = useLocation();
  const isActive = (path) => location.pathname === path;

  return (
<nav className="flex items-center justify-between py-6 px-10 bg-white dark:bg-gray-900 shadow-lg relative">
  {/* Centered Title (Absolute + Responsive) */}
  <div className="absolute left-1/2 transform -translate-x-1/2 text-[1.75rem] md:text-3xl font-extrabold text-gray-800 dark:text-white whitespace-nowrap text-center">
    Data Compression & Decompression Portal
  </div>

  {/* Spacer to push right items */}
  <div className="flex items-center gap-6 ml-auto z-10">
    <ul className="flex gap-6 text-lg font-semibold text-gray-700 dark:text-gray-200">
      <li>
        <Link
          to="/"
          className={`hover:text-blue-500 dark:hover:text-yellow-300 ${
            location.pathname === "/" ? "text-blue-600 dark:text-yellow-300 underline" : ""
          }`}
        >
          Upload
        </Link>
      </li>
      <li>
        <Link
          to="/statistics"
          className={`hover:text-blue-500 dark:hover:text-yellow-300 ${
            location.pathname === "/statistics" ? "text-blue-600 dark:text-yellow-300 underline" : ""
          }`}
        >
          Statistics
        </Link>
      </li>
      <li>
        <Link
          to="/about"
          className={`hover:text-blue-500 dark:hover:text-yellow-300 ${
            location.pathname === "/about" ? "text-blue-600 dark:text-yellow-300 underline" : ""
          }`}
        >
          About
        </Link>
      </li>
    </ul>

    <div className="mt-2">
      <ThemeToggle />
    </div>
  </div>
</nav>

  );
};

export default Navbar;
