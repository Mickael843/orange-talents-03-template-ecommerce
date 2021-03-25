package com.mikkaeru.ecommerce.dto.out.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mikkaeru.ecommerce.model.product.ProductImage;

public class ProductImageResponse {

    private String link;

    @JsonCreator
    public ProductImageResponse(@JsonProperty("link") ProductImage image) {
        this.link = image.getLink();
    }

    public String getLink() {
        return link;
    }
}
