package com.mikkaeru.ecommerce.model.product;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class ProductImage {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String link;
    @ManyToOne(optional = false)
    private Product product;

    /**
     * @deprecated hibernate only
     */
    public ProductImage() { }

    public ProductImage(String link, Product product) {
        this.link = link;
        this.product = product;
    }

    public String getLink() {
        return link;
    }
}
