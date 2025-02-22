package com.mikkaeru.ecommerce.dto.in.buy;

import com.mikkaeru.ecommerce.model.buy.PaymentGateway;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ProductBuyRequest {

    private final @NotNull Long productId;
    private final @NotNull @Positive int amount;
    private final @NotNull PaymentGateway gateway;

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
