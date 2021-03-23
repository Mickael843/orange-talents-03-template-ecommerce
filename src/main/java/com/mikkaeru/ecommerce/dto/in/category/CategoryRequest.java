package com.mikkaeru.ecommerce.dto.in.category;

import com.mikkaeru.ecommerce.model.category.Category;
import com.mikkaeru.ecommerce.repository.category.CategoryRepository;
import com.mikkaeru.ecommerce.validator.UniqueValue;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class CategoryRequest {

    @UniqueValue(fieldName = "name", domainClass = Category.class)
    private @NotBlank final String name;
    private final long motherCategoryId;

    public CategoryRequest(@NotBlank String name, long motherCategoryId) {
        this.name = name;
        this.motherCategoryId = motherCategoryId;
    }

    public Category toModel(CategoryRepository categoryRepository) {
        Optional<Category> categoryOptional = categoryRepository.findById(motherCategoryId);
        return categoryOptional.map(value -> new Category(name, value)).orElseGet(() -> new Category(name));
    }
}
