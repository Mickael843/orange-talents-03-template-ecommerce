package com.mikkaeru.ecommerce.dto.in.user;

import com.mikkaeru.ecommerce.domain.model.User;
import com.mikkaeru.ecommerce.validator.UniqueValue;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRequest {

    @UniqueValue(fieldName = "login", domainClass = User.class)
    private @NotBlank @Email final String login;
    private @NotBlank @Size(min = 6) final String password;

    public UserRequest(@Email @NotBlank String login, @NotBlank @Size(min = 6) String password) {
        this.login = login;
        this.password = password;
    }

    public User toModel() {
        return new User(login, new BCryptPasswordEncoder().encode(password));
    }
}
