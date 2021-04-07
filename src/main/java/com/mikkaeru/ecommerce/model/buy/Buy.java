package com.mikkaeru.ecommerce.model.buy;

import com.mikkaeru.ecommerce.dto.in.buy.TransactionRequest;
import com.mikkaeru.ecommerce.model.product.Product;
import com.mikkaeru.ecommerce.model.user.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.mikkaeru.ecommerce.model.buy.PurchaseStatus.INITIATED;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Buy {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(nullable = false)
    private BigDecimal price;
    @ManyToOne(optional = false)
    private Product product;
    @Column(nullable = false)
    private Integer amount;
    @ManyToOne(optional = false)
    private User loggedUser;
    @Enumerated(STRING)
    @Column(nullable = false)
    private PaymentGateway gateway;
    @Enumerated(STRING)
    @Column(nullable = false)
    private PurchaseStatus status = INITIATED;
    @Column(nullable = false, unique = true)
    private UUID code = UUID.randomUUID();
    @OneToMany(mappedBy = "buy", cascade = MERGE)
    private Set<Transaction> transactions = new HashSet<>();

    /**
     * @deprecated hibernate only
     */
    public Buy() { }

    public Buy(Product product, BigDecimal price, int amount, User loggedUser, PaymentGateway gateway) {
        this.product = product;
        this.amount = amount;
        this.loggedUser = loggedUser;
        this.gateway = gateway;
        this.price = calculatePrice(price);
    }

    private BigDecimal calculatePrice(BigDecimal price) {
        if (amount > 1) {
            return  price.multiply(new BigDecimal(amount));
        }

        return price;
    }

    public Long getId() {
        return id;
    }

    public UUID getCode() {
        return code;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public Product getProduct() {
        return product;
    }

    public boolean paymentAttempt(TransactionRequest request) {
        Transaction transaction = request.toModel(this);

        if (this.transactions.contains(transaction)) {
            return false;
        }

        Set<Transaction> transactionsCompletedSuccessfully = this.transactions.stream()
                .filter(Transaction::successfullyCompleted).collect(Collectors.toSet());

        if (!transactionsCompletedSuccessfully.isEmpty()) {
            return false;
        }

        this.transactions.add(transaction);
        return true;
    }
}
