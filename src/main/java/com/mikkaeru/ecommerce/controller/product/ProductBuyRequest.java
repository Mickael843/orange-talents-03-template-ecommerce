package com.mikkaeru.ecommerce.controller.product;

import com.mikkaeru.ecommerce.model.buy.PaymentGateway;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ProductBuyRequest {

    private @NotNull Long productId;
    private @NotNull @Positive int amount;
    private @NotNull PaymentGateway gateway;

    public ProductBuyRequest(@NotNull @Positive int amount, @NotNull Long productId, @NotNull PaymentGateway gateway) {
        this.amount = amount;
        this.gateway = gateway;
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public Long getProductId() {
        return productId;
    }

    public PaymentGateway getGateway() {
        return gateway;
    }
}
