package com.mikkaeru.ecommerce.model.user;

import com.mikkaeru.ecommerce.model.product.Product;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "tb_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String login;
    @Column(nullable = false)
    private String password;
    @CreationTimestamp
    private OffsetDateTime createAt = OffsetDateTime.now();
    @OneToMany(mappedBy = "owner")
    private List<Product> products = new ArrayList<>();

    /**
     * @deprecated hibernate only
     */
    public User() { }

    public User(@NotBlank @Email String login, @Valid CleanPassword cleanPassword) {
        this.login = login;
        this.password = cleanPassword.hash();
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
