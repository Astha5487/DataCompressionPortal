import React from "react";
import {
  BarChart, Bar, XAxis, YAxis, Tooltip, Legend, ResponsiveContainer
} from "recharts";

const CompressionStats = ({ data }) => {
  if (data.length === 0) return <p>No compression data yet.</p>;

  return (
    <div className="p-4 shadow rounded bg-white mt-4">
      <h2 className="text-xl font-semibold mb-4">📊 Compression Comparison</h2>
      <ResponsiveContainer width="100%" height={300}>
        <BarChart data={data}>
          <XAxis dataKey="algorithm" />
          <YAxis />
          <Tooltip />
          <Legend />
          <Bar dataKey="compressionRatio" fill="#8884d8" name="Compression Ratio" />
          <Bar dataKey="entropy" fill="#82ca9d" name="Entropy" />
          <Bar dataKey="speedMBperSec" fill="#ffc658" name="Speed (MB/s)" />
        </BarChart>
      </ResponsiveContainer>
    </div>
  );
};

export default CompressionStats;
