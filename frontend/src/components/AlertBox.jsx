import React from 'react';

const AlertBox = ({ type = 'info', message, onClose }) => {
  const colors = {
    info: 'bg-blue-100 text-blue-800',
    success: 'bg-green-100 text-green-800',
    error: 'bg-red-100 text-red-800',
  };
  return (
    <div className={`p-4 rounded ${colors[type]} mb-4 flex justify-between items-center`}>
      <span>{message}</span>
      {onClose && (
        <button onClick={onClose} className="text-xl leading-none">×</button>
      )}
    </div>
  );
};

export default AlertBox;
