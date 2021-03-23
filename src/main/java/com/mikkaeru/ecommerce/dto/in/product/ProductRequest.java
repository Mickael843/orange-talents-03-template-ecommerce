package com.mikkaeru.ecommerce.dto.in.product;

import com.mikkaeru.ecommerce.dto.in.characteristic.CharacteristicRequest;
import com.mikkaeru.ecommerce.model.category.Category;
import com.mikkaeru.ecommerce.model.product.Product;
import com.mikkaeru.ecommerce.model.user.User;
import com.mikkaeru.ecommerce.repository.category.CategoryRepository;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static io.jsonwebtoken.lang.Assert.notNull;

public class ProductRequest {

    private final @NotBlank String name;
    private final @NotNull @Positive BigDecimal price;
    private final @NotNull @PositiveOrZero int availableQuantity;
    private final @NotBlank @Length(max = 1000) String description;
    private final @NotNull @Size(min = 3) List<CharacteristicRequest> characteristics;
    private final @NotNull long categoryId;
    private User userAuthenticated;

    public ProductRequest(@NotBlank String name, @NotNull @Positive BigDecimal price,
                          @NotBlank @PositiveOrZero int availableQuantity,
                          @NotBlank @Length(max = 1000) String description,
                          @NotNull @Size(min = 3) List<CharacteristicRequest> characteristics, @NotNull long categoryId) {
        this.name = name;
        this.price = price;
        this.availableQuantity = availableQuantity;
        this.description = description;
        this.characteristics = characteristics;
        this.categoryId = categoryId;
    }

    public Product toModel(CategoryRepository categoryRepository) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);

        if (categoryOptional.isEmpty()) {
            throw new EntityNotFoundException("Não foi encontrado uma categoria para esse produto!");
        }

        return new Product(name, price, availableQuantity, description, categoryOptional.get(), userAuthenticated);
    }

    public void setUserAuthenticated(User user) {
        notNull(user, "O usuário autenticado não pode vir nulo!");
        this.userAuthenticated = user;
    }

    public List<CharacteristicRequest> getCharacteristics() {
        return characteristics;
    }
}
