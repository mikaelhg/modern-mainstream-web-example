import tailwindcss from '@tailwindcss/vite'
import react from '@vitejs/plugin-react'
import { type UserConfig } from 'vite'

export default {
  plugins: [react(), tailwindcss()],
  server: {
    host: true,
    port: 3000,
    proxy: {
      '/api': {
        target: 'http://localhost:20080',
        changeOrigin: true,
        secure: false,
      },
    },
  },
  build: {
    outDir: 'dist',
    sourcemap: true,
  },
} satisfies UserConfig
