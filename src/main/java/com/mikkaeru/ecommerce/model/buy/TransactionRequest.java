package com.mikkaeru.ecommerce.model.buy;

public interface TransactionRequest {

    Transaction toModel(Buy buy);
}
