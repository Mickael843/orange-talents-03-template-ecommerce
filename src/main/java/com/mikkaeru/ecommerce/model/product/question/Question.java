package com.mikkaeru.ecommerce.model.product.question;

import com.mikkaeru.ecommerce.model.product.Product;
import com.mikkaeru.ecommerce.model.user.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @ManyToOne(optional = false)
    private User loggedUser;
    @ManyToOne(optional = false)
    private Product product;
    @CreationTimestamp
    private OffsetDateTime createAt = OffsetDateTime.now();

    /**
     * @deprecated hibernate only
     */
    public Question() { }

    public Question(@NotBlank String title, User loggedUser, Product product) {
        this.title = title;
        this.loggedUser = loggedUser;
        this.product = product;
    }

    public String getTitle() {
        return title;
    }
}
