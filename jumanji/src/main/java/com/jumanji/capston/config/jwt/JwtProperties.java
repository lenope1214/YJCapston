package com.jumanji.capston.config.jwt;

public interface JwtProperties {
    String SECRET = "jumanjiSecretKey";
    int EXPIRATION_TIME = 30 * 60 * 1000; //
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
