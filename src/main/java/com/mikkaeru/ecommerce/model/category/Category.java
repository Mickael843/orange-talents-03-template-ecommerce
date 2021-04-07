package com.mikkaeru.ecommerce.model.category;

import com.mikkaeru.ecommerce.model.product.Product;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    private Category category;
    @OneToMany(mappedBy = "category", cascade = MERGE)
    private List<Product> products = new ArrayList<>();

    /**
     * @deprecated hibernate only
     */
    public Category() { }

    public Category(@NotBlank String name) {
        this.name = name;
    }

    public Category(@NotBlank String name, Category category) {
        this.name = name;
        this.category = category;
    }
}
