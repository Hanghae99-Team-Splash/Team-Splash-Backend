package com.splash.teamsplashbackend.jwt;

public interface JwtProperties {
    String SECRET = "버민";
    int EXPIRATION_TIME = 60 * 30 * 1000;
    String HEADER_STRING = "Authorization";
}
