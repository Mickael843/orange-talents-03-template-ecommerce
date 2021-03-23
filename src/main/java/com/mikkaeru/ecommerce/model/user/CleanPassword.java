package com.mikkaeru.ecommerce.model.user;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;

import static org.springframework.util.Assert.hasLength;
import static org.springframework.util.Assert.isTrue;

public class CleanPassword {

    @NotBlank
    @Length(min = 6)
    private final String password;

    public CleanPassword(@NotBlank @Length(min = 6) String password) {
        hasLength(password, "Senha não pode estar nulo!");
        isTrue(password.length() >= 6, "Senha deve ter no mínimo 6 caracteres");

        this.password = password;
    }

    public String hash() {
        return new BCryptPasswordEncoder().encode(password);
    }
}
