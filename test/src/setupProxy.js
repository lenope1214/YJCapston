const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = (app) => {
    app.use(
        "/api",
        createProxyMiddleware({
            target: "ws://3.34.55.186:8088",
            changeOrigin: true,
        })
    );
    app.use(
        "/ws-stomp",
        createProxyMiddleware({ target: "ws://3.34.55.186:8088", ws: true })
    );
};
