package com.mikkaeru.ecommerce.model.category;

import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static javax.persistence.GenerationType.IDENTITY;
import static org.springframework.util.Assert.isTrue;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    private Category category;

    @Deprecated
    public Category() { }

    public Category(@NotBlank String name) {
        isTrue(StringUtils.hasLength(name), "O nome da categoria não pode ser nulo!");

        this.name = name;
    }

    public Category(@NotBlank String name, Category category) {
        isTrue(StringUtils.hasLength(name), "O nome da categoria não pode ser nulo!");

        this.name = name;
        this.category = category;
    }
}
