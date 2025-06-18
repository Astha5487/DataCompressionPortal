import React, { useEffect, useState } from 'react';

const FilePreview = ({ file }) => {
  const [content, setContent] = useState('');

  useEffect(() => {
    if (file.type.startsWith('text/')) {
      const reader = new FileReader();
      reader.onload = e => setContent(e.target.result);
      reader.readAsText(file);
    } else if (file.type.startsWith('image/')) {
      setContent(URL.createObjectURL(file));
    } else {
      setContent(null);
    }
  }, [file]);

  return (
    <div className="mt-4">
      <h4 className="font-medium mb-2">Preview:</h4>
      {content ? (
        file.type.startsWith('text/') ? (
          <pre className="bg-gray-100 p-2 rounded h-48 overflow-auto">{content.slice(0, 1000)}</pre>
        ) : (
          <img src={content} alt="preview" className="max-h-64 rounded" />
        )
      ) : (
        <p>Preview not available</p>
      )}
    </div>
  );
};

export default FilePreview;
