package com.mikkaeru.ecommerce.dto.out.product.opinion;

import com.mikkaeru.ecommerce.model.product.opinion.Opinion;

public class OpinionResponse {

    private String title;
    private String description;
    private Integer rating;

    public OpinionResponse(Opinion opinion) {
        this.rating = opinion.getRating();
        this.title = opinion.getTitle();
        this.description = opinion.getDescription();
    }

    public Integer getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
