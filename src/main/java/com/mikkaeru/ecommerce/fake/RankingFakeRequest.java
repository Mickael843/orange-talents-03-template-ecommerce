package com.mikkaeru.ecommerce.fake;

import javax.validation.constraints.NotNull;

public class RankingFakeRequest {

    private final @NotNull long buyId;
    private final @NotNull long sellerId;

    public RankingFakeRequest(@NotNull long buyId, @NotNull long sellerId) {
        this.buyId = buyId;
        this.sellerId = sellerId;
    }

    public long getSellerId() {
        return sellerId;
    }
}
