import React from 'react';

const Sidebar = () => (
  <aside className="hidden md:block w-64 bg-gray-800 text-white p-4 fixed h-full">
    <h2 className="text-xl font-bold mb-6">Navigation</h2>
    <nav className="space-y-4">
      <a href="#upload" className="block hover:underline">Upload</a>
      <a href="#stats" className="block hover:underline">Statistics</a>
      <a href="#about" className="block hover:underline">About</a>
    </nav>
  </aside>
);

export default Sidebar;
