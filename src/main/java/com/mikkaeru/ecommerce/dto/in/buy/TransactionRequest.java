package com.mikkaeru.ecommerce.dto.in.buy;

import com.mikkaeru.ecommerce.model.buy.Buy;
import com.mikkaeru.ecommerce.model.buy.Transaction;

public interface TransactionRequest {

    Transaction toModel(Buy buy);
}
