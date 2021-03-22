package com.mikkaeru.ecommerce.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private @NotBlank @Email String login;
    @Column(nullable = false)
    private @NotBlank String password;
    @Column(nullable = false)
    private OffsetDateTime createAt = OffsetDateTime.now();

    @Deprecated
    public User() { }

    public User(@NotBlank @Email String login, @NotBlank String password) {
        this.login = login;
        this.password = password;
    }
}
