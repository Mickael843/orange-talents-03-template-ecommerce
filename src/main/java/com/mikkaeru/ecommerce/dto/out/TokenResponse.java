package com.mikkaeru.ecommerce.dto.out;

import org.springframework.util.StringUtils;

import static io.jsonwebtoken.lang.Assert.isTrue;

public class TokenResponse {

    private final String type;
    private final String token;

    public TokenResponse(String type, String token) {
        isTrue(StringUtils.hasLength(token), "O token não pode estar em branco!");
        isTrue(StringUtils.hasLength(type), "O tipo do token não pode estar em branco!");

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
