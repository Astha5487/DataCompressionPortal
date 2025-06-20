import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
  server: {
    port: 5173,      // ⬅️ Forces Vite to use port 5173
    strictPort: true // ⬅️ Fails if 5173 is not available (no auto-switch)
  },
});
