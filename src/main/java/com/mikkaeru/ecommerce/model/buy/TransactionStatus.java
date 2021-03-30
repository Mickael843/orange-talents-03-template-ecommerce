package com.mikkaeru.ecommerce.model.buy;

public enum TransactionStatus {

    SUCCESS, ERROR;

    public TransactionStatus normalize() {
        if (this.equals(SUCCESS)) {
            return SUCCESS;
        }

        return ERROR;
    }
}
