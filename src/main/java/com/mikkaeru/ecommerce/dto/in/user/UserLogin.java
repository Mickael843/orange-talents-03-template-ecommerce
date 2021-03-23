package com.mikkaeru.ecommerce.dto.in.user;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserLogin {

    private @NotBlank @Email final String login;
    private @NotBlank @Length(min = 6) final String password;

    public UserLogin(@Email @NotBlank String login, @NotBlank @Length(min = 6) String password) {
        this.login = login;
        this.password = password;
    }

    public UsernamePasswordAuthenticationToken toAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(login, password);
    }
}
