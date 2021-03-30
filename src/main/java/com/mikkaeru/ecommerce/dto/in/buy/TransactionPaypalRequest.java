package com.mikkaeru.ecommerce.dto.in.buy;

import com.mikkaeru.ecommerce.model.buy.Buy;
import com.mikkaeru.ecommerce.model.buy.Transaction;
import com.mikkaeru.ecommerce.model.buy.TransactionRequest;
import com.mikkaeru.ecommerce.model.buy.TransactionStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.mikkaeru.ecommerce.model.buy.TransactionStatus.ERROR;
import static com.mikkaeru.ecommerce.model.buy.TransactionStatus.SUCCESS;

public class TransactionPaypalRequest implements TransactionRequest {

    private final @NotNull int status;
    private final @NotBlank String transactionId;

    public TransactionPaypalRequest(@NotNull int status, @NotBlank String transactionId) {
        this.status = status;
        this.transactionId = transactionId;
    }

    public Transaction toModel(Buy buy) {
        TransactionStatus status = this.status == 0 ? ERROR : SUCCESS;
        return new Transaction(buy, status, this.transactionId);
    }
}
