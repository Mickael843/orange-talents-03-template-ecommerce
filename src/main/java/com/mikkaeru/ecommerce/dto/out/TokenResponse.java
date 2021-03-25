package com.mikkaeru.ecommerce.dto.out;

public class TokenResponse {

    private final String type;
    private final String token;

    public TokenResponse(String type, String token) {
        this.type = type;
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public String getToken() {
        return token;
    }
}
