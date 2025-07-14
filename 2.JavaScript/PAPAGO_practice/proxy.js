const express = require('express');
const { createProxyMiddleware } = require('http-proxy-middleware');

const app = express();
const PORT = 3000;

// 프록시 미들웨어 설정
app.use('/proxy', createProxyMiddleware({
    target: 'https://api.github.com', // 프록시할 타겟 URL
    changeOrigin: true,
    pathRewrite: {
        '^/proxy': '', // /proxy 경로를 타겟 URL에 맞게 재작성
    },
    onProxyReq(proxyReq, req, res) {
        // 헤더 추가 (필요 시)
        proxyReq.setHeader('User-Agent', 'Mozilla/5.0');
    }
}));

app.listen(PORT, () => {
    console.log(`Proxy server is running on http://localhost:${PORT}`);
});