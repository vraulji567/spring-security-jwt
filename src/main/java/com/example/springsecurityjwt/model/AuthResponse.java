package com.example.springsecurityjwt.model;

public class AuthResponse {

    private TokenInfo token;

    public AuthResponse(TokenInfo token) {
        this.token = token;
    }

    public TokenInfo getToken() {
        return token;
    }

    public void setToken(TokenInfo token) {
        this.token = token;
    }
}
