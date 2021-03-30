package com.mikkaeru.ecommerce.dto.in.buy;

import com.mikkaeru.ecommerce.model.buy.Buy;
import com.mikkaeru.ecommerce.model.buy.Transaction;
import com.mikkaeru.ecommerce.model.buy.TransactionRequest;
import com.mikkaeru.ecommerce.model.buy.TransactionStatus;

import javax.validation.constraints.NotNull;

public class TransactionPagseguroRequest implements TransactionRequest {

    private final @NotNull String transactionId;
    private final @NotNull TransactionStatus status;

    public TransactionPagseguroRequest(@NotNull String transactionId, @NotNull TransactionStatus status) {
        this.status = status;
        this.transactionId = transactionId;
    }

    public Transaction toModel(Buy buy) {
        return new Transaction(buy, status.normalize(), transactionId);
    }
}
