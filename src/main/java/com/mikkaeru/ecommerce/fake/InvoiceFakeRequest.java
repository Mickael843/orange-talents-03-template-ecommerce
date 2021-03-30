package com.mikkaeru.ecommerce.fake;

import javax.validation.constraints.NotNull;

public class InvoiceFakeRequest {

    private final @NotNull long buyId;
    private final @NotNull long buyerId;

    public InvoiceFakeRequest(@NotNull long buyId, @NotNull long buyerId) {
        this.buyId = buyId;
        this.buyerId = buyerId;
    }

    @Override
    public String toString() {
        return "InvoiceFakeRequest{" +
                "buyId=" + buyId +
                ", buyerId=" + buyerId +
                '}';
    }
}
