import React from 'react';

const AlgorithmSelector = ({ selected, onChange }) => {
  const algorithms = ['Huffman', 'Run-Length Encoding', 'LZ77'];
  return (
    <div className="mb-4">
      <label className="block font-medium mb-1">Choose Algorithm</label>
      <select
        className="w-full p-2 border rounded"
        value={selected}
        onChange={(e) => onChange(e.target.value)}
      >
        <option value="" disabled>Select one...</option>
        {algorithms.map(algo => (
          <option key={algo} value={algo}>{algo}</option>
        ))}
      </select>
    </div>
  );
};

export default AlgorithmSelector;
