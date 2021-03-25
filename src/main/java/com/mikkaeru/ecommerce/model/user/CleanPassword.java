package com.mikkaeru.ecommerce.model.user;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;

public class CleanPassword {

    @NotBlank
    @Length(min = 6)
    private final String password;

    public CleanPassword(@NotBlank @Length(min = 6) String password) {
        this.password = password;
    }

    public String hash() {
        return new BCryptPasswordEncoder().encode(password);
    }
}
