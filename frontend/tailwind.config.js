// tailwind.config.js
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  darkMode: "class", // ✅ Important for class-based dark mode toggle
  theme: {
    extend: {},
  },
  plugins: [],
};
