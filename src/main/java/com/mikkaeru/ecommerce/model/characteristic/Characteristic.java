package com.mikkaeru.ecommerce.model.characteristic;

import com.mikkaeru.ecommerce.model.product.Product;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static io.jsonwebtoken.lang.Assert.isTrue;
import static io.jsonwebtoken.lang.Assert.notNull;
import static javax.persistence.GenerationType.IDENTITY;
import static org.springframework.util.StringUtils.hasLength;

@Entity
public class Characteristic {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @ManyToOne(optional = false)
    private Product product;

    public Characteristic(@NotBlank String name, @NotBlank String description, @NotNull Product product) {
        isTrue(hasLength(name), "O nome da característica não pode estar nulo!");
        isTrue(hasLength(description), "A descrição da característica não pode estar nulo!");
        notNull(product, "O produto não pode ser nulo!");

        this.name = name;
        this.description = description;
        this.product = product;
    }
}
