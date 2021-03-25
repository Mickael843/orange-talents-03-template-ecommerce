package com.mikkaeru.ecommerce.dto.in.product.opinion;

import com.mikkaeru.ecommerce.model.product.Product;
import com.mikkaeru.ecommerce.model.product.opinion.Opinion;
import com.mikkaeru.ecommerce.model.user.User;
import com.mikkaeru.ecommerce.repository.product.ProductRepository;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class OpinionRequest {

    private final @Min(1) @Max(5) int rating;
    private final @NotBlank String title;
    private final @NotBlank @Length(max = 500) String description;
    private long productId;
    private User loggedUser;

    public OpinionRequest(@Min(1) @Max(5) int rating, @NotBlank String title, @NotBlank @Length(max = 500) String description) {
        this.rating = rating;
        this.title = title;
        this.description = description;
    }

    public void setLoggedUser(@NotNull User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public Opinion toModel(ProductRepository productRepository) {
        Optional<Product> product = productRepository.findById(productId);

        if (product.isEmpty()) {
            throw new EntityNotFoundException("O produto especificado não foi encontrado!");
        }

        return new Opinion(rating, title, description, product.get(), loggedUser);
    }
}
