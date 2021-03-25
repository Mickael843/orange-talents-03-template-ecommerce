package com.mikkaeru.ecommerce.model.product.opinion;

import com.mikkaeru.ecommerce.model.product.Product;
import com.mikkaeru.ecommerce.model.user.User;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Opinion {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer rating;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, length = 500)
    private String description;
    @ManyToOne(optional = false)
    private Product product;
    @ManyToOne(optional = false)
    private User user;

    @Deprecated
    public Opinion() { }

    public Opinion(@Min(1) @Max(5) int rating, @NotBlank String title,
                   @NotBlank @Length(max = 500) String description, Product product, User loggedUser) {
        this.rating = rating;
        this.title = title;
        this.description = description;
        this.product = product;
        this.user = loggedUser;
    }

    public Integer getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
