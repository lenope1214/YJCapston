package com.jumanji.capston.config.jwt;

public interface JwtProperties {
    String SECRET = "jumanjiSecretKey";
    //                    일  시    분   초   밀리세컨
    long EXPIRATION_TIME = 5 *24 * 60 * 60 * 1000; // JWT 유효시간
    long REFRESH_TOKEN_VALIDATION_SECOND = 1000L * 60 * 24 * 2; // RefreshToken 유효시간
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
    String REFRESH_TOKEN_NAME = "refreshToken";
}
