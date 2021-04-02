package com.mikkaeru.ecommerce.dto.out.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mikkaeru.ecommerce.dto.out.characteristic.CharacteristicResponse;
import com.mikkaeru.ecommerce.dto.out.product.opinion.OpinionResponse;
import com.mikkaeru.ecommerce.dto.out.product.question.QuestionResponse;
import com.mikkaeru.ecommerce.model.product.Product;
import com.mikkaeru.ecommerce.repository.product.opinion.OpinionRepository;

import java.math.BigDecimal;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class ProductResponse {

    private final String name;
    private final BigDecimal price;
    private final String description;
    private int totalRating;
    private double averageRating;
    private final Integer availableQuantity;
    private final List<OpinionResponse> opinions;
    private final List<QuestionResponse> questions;
    private final List<ProductImageResponse> images;
    private final List<CharacteristicResponse> characteristics;

    public ProductResponse(Product product, OpinionRepository opinionRepository) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.availableQuantity = product.getAvailableQuantity();

        if (product.canCalculate()) {
            this.averageRating = opinionRepository.getAverageRating(product.getId());
            this.totalRating = opinionRepository.getTotalRating(product.getId());
        }

        this.images = product.mapImages();
        this.opinions = product.mapOpinions();
        this.questions = product.mapQuestions();
        this.characteristics = product.mapCharacteristics();
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getTotalRating() {
        return totalRating;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public List<OpinionResponse> getOpinions() {
        return opinions;
    }

    public List<QuestionResponse> getQuestions() {
        return questions;
    }

    public List<ProductImageResponse> getImages() {
        return images;
    }

    public List<CharacteristicResponse> getCharacteristics() {
        return characteristics;
    }
}
