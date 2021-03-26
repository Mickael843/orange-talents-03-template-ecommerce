package com.mikkaeru.ecommerce.model.buy;

import com.mikkaeru.ecommerce.model.product.Product;
import com.mikkaeru.ecommerce.model.user.User;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Buy {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private Product product;
    @Column(nullable = false)
    private Integer amount;
    @ManyToOne(optional = false)
    private User loggedUser;
    @Enumerated(STRING)
    @Column(nullable = false)
    private PaymentGateway gateway;

    @Deprecated
    public Buy() { }

    public Buy(Product product, int amount, User loggedUser, PaymentGateway gateway) {
        this.product = product;
        this.amount = amount;
        this.loggedUser = loggedUser;
        this.gateway = gateway;
    }

    public Long getId() {
        return id;
    }
}
