package com.mikkaeru.ecommerce.model.product;

import com.mikkaeru.ecommerce.dto.out.characteristic.CharacteristicResponse;
import com.mikkaeru.ecommerce.dto.out.product.ProductImageResponse;
import com.mikkaeru.ecommerce.dto.out.product.opinion.OpinionResponse;
import com.mikkaeru.ecommerce.dto.out.product.question.QuestionResponse;
import com.mikkaeru.ecommerce.model.category.Category;
import com.mikkaeru.ecommerce.model.characteristic.Characteristic;
import com.mikkaeru.ecommerce.model.product.opinion.Opinion;
import com.mikkaeru.ecommerce.model.product.question.Question;
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
import java.util.stream.Collectors;

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
    @ManyToOne(optional = false)
    private Category category;
    @ManyToOne(optional = false)
    private User owner;
    @OneToMany(mappedBy = "product")
    private List<ProductImage> images = new ArrayList<>();
    @OneToMany(mappedBy = "product")
    private List<Opinion> opinions = new ArrayList<>();
    @OneToMany(mappedBy = "product")
    private List<Question> questions = new ArrayList<>();
    @OneToMany(mappedBy = "product")
    private List<Characteristic> characteristics = new ArrayList<>();
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

    public boolean isFromUser(User user) {
        return this.owner.equals(user);
    }

    public Long getId() {
        return id;
    }

    public User getOwner() {
        return this.owner;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public String getDescription() {
        return description;
    }

    public List<QuestionResponse> mapQuestions() {
        return questions.stream().map(QuestionResponse::new).collect(Collectors.toList());
    }

    public List<ProductImageResponse> mapImages() {
        return images.stream().map(ProductImageResponse::new).collect(Collectors.toList());
    }

    public List<OpinionResponse> mapOpinions() {
        return opinions.stream().map(OpinionResponse::new).collect(Collectors.toList());
    }

    public List<CharacteristicResponse> mapCharacteristics() {
        return characteristics.stream().map(CharacteristicResponse::new).collect(Collectors.toList());
    }

    public boolean removeFromStock(int amount) {
        if (this.availableQuantity >= amount) {
            this.availableQuantity -= amount;
            return true;
        }

        return false;
    }
}
