import React from "react";
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  Tooltip,
  Legend,
  ResponsiveContainer,
} from "recharts";

const Statistics = ({ results }) => {
  return (
    <div className="bg-white dark:bg-gray-900 p-4 rounded-lg shadow text-gray-900 dark:text-gray-100">
  <h2 className="text-xl font-semibold mb-4">📊 Comparison Chart</h2>

      {results.length === 0 ? (
        <p className="text-gray-600">No data available. Please compress some files first.</p>
      ) : (
        <div className="space-y-10">
          {/* Compression Ratio */}
          <div>
            <h3 className="text-lg font-semibold mb-2">Compression Ratio</h3>
            <ResponsiveContainer width="100%" height={300}>
              <BarChart data={results}>
                <XAxis dataKey="algorithm" />
                <YAxis />
                <Tooltip />
                <Legend />
                <Bar dataKey="compressionRatio" fill="#3b82f6" />
              </BarChart>
            </ResponsiveContainer>
          </div>

          {/* Processing Time */}
          <div>
            <h3 className="text-lg font-semibold mb-2">Processing Time (ms)</h3>
            <ResponsiveContainer width="100%" height={300}>
              <BarChart data={results}>
                <XAxis dataKey="algorithm" />
                <YAxis />
                <Tooltip />
                <Legend />
                <Bar dataKey="processingTimeMillis" fill="#10b981" />
              </BarChart>
            </ResponsiveContainer>
          </div>

          {/* Speed */}
          <div>
            <h3 className="text-lg font-semibold mb-2">Speed (MB/s)</h3>
            <ResponsiveContainer width="100%" height={300}>
              <BarChart data={results}>
                <XAxis dataKey="algorithm" />
                <YAxis />
                <Tooltip />
                <Legend />
                <Bar dataKey="speedMBperSec" fill="#f59e0b" />
              </BarChart>
            </ResponsiveContainer>
          </div>
        </div>
      )}
    </div>
  );
};

export default Statistics;
