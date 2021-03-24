package com.mikkaeru.ecommerce.model.product;

import com.mikkaeru.ecommerce.model.category.Category;
import com.mikkaeru.ecommerce.model.characteristic.Characteristic;
import com.mikkaeru.ecommerce.model.user.User;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Integer availableQuantity;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    @OneToMany(mappedBy = "product")
    private List<Characteristic> characteristics;
    @ManyToOne(optional = false)
    private Category category;
    @ManyToOne(optional = false)
    private User owner;
    @OneToMany(mappedBy = "product", cascade = MERGE)
    private List<ProductImage> images = new ArrayList<>();
    private OffsetDateTime createAt = OffsetDateTime.now();

    @Deprecated
    public Product() { }

    public Product(@NotBlank String name, @NotNull @Positive BigDecimal price,
                   @NotBlank @PositiveOrZero int availableQuantity, @NotBlank @Length(max = 1000) String description, Category category , @NotNull User owner) {
        this.name = name;
        this.price = price;
        this.availableQuantity = availableQuantity;
        this.description = description;
        this.category = category;
        this.owner = owner;
    }
}
