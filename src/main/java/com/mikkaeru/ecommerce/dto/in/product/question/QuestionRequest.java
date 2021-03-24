package com.mikkaeru.ecommerce.dto.in.product.question;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mikkaeru.ecommerce.model.product.Product;
import com.mikkaeru.ecommerce.model.product.question.Question;
import com.mikkaeru.ecommerce.model.user.User;
import com.mikkaeru.ecommerce.repository.product.ProductRepository;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class QuestionRequest {

    private long productId;
    private User loggedUser;
    private User productOwner;
    private @NotBlank final String title;

    public QuestionRequest(@JsonProperty("title") @NotBlank String title) {
        this.title = title;
    }

    public User getProductOwner() {
        return this.productOwner;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public Question toModel(ProductRepository productRepository) {
        Optional<Product> product = productRepository.findById(productId);

        if (product.isEmpty()) {
            throw new EntityNotFoundException("O produto especificado não foi encontrado!");
        }

        this.productOwner = product.get().getOwner();

        return new Question(title, loggedUser, product.get());
    }
}
