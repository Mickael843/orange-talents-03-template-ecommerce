package com.mikkaeru.ecommerce.dto.in.user;

import com.mikkaeru.ecommerce.model.user.CleanPassword;
import com.mikkaeru.ecommerce.model.user.User;
import com.mikkaeru.ecommerce.validator.UniqueValue;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserRequest {

    @UniqueValue(fieldName = "login", domainClass = User.class)
    private @NotBlank @Email final String login;
    private @NotBlank @Length(min = 6) final String password;

    public UserRequest(@Email @NotBlank String login, @NotBlank @Length(min = 6) String password) {
        this.login = login;
        this.password = password;
    }

    public User toModel() {
        return new User(login, new CleanPassword(password));
    }
}
