package com.mikkaeru.ecommerce.model.characteristic;

import com.mikkaeru.ecommerce.model.product.Product;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.IDENTITY;

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

    /**
     * @deprecated hibernate only
     */
    public Characteristic() { }

    public Characteristic(@NotBlank String name, @NotBlank String description, @NotNull Product product) {
        this.name = name;
        this.description = description;
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
