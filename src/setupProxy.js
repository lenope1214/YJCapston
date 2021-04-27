const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function (app) {
    app.use(
        "/api",
        createProxyMiddleware({
            target: "https://api.iamport.kr/users/getToken",
            changeOrigin: true,
        })
    );
};
