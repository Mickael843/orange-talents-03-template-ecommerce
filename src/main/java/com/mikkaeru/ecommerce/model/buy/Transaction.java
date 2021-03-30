package com.mikkaeru.ecommerce.model.buy;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;

import static com.mikkaeru.ecommerce.model.buy.TransactionStatus.SUCCESS;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private Buy buy;
    @Column(nullable = false)
    private String transactionId;
    @Enumerated(STRING)
    @Column(nullable = false)
    private TransactionStatus status;
    @Column(nullable = false)
    private OffsetDateTime createAt;

    @Deprecated
    public Transaction() {
    }

    public Transaction(Buy buy, TransactionStatus status, String transactionId) {
        this.buy = buy;
        this.status = status;
        this.transactionId = transactionId;
        this.createAt =  OffsetDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(transactionId, that.transactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId);
    }

    public boolean successfullyCompleted() {
        return this.status.equals(SUCCESS);
    }
}
