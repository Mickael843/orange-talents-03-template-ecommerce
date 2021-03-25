package com.mikkaeru.ecommerce.dto.out.product.question;

import com.mikkaeru.ecommerce.model.product.question.Question;

public class QuestionResponse {

    private String title;

    public QuestionResponse(Question question) {
        this.title = question.getTitle();
    }

    public String getTitle() {
        return title;
    }
}
