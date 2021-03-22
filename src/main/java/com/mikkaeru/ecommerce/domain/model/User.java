package com.mikkaeru.ecommerce.domain.model;

import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;

import static javax.persistence.GenerationType.IDENTITY;
import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

@Entity(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String login;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private OffsetDateTime createAt = OffsetDateTime.now();

    @Deprecated
    public User() { }

    public User(@NotBlank @Email String login, @Valid CleanPassword cleanPassword) {
        isTrue(StringUtils.hasLength(login), "Login não pode ser nulo!");
        notNull(cleanPassword, "A senha não pode ser nula!");

        this.login = login;
        this.password = cleanPassword.hash();
    }
}
