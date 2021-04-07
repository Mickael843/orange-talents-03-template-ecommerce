package com.mikkaeru.ecommerce.controller.buy;

import com.mikkaeru.ecommerce.dto.in.buy.TransactionPagseguroRequest;
import com.mikkaeru.ecommerce.dto.in.buy.TransactionPaypalRequest;
import com.mikkaeru.ecommerce.listener.BuyEvent;
import com.mikkaeru.ecommerce.model.buy.Buy;
import com.mikkaeru.ecommerce.dto.in.buy.TransactionRequest;
import com.mikkaeru.ecommerce.repository.buy.BuyRepository;
import com.mikkaeru.ecommerce.service.ProcessPayment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
public class TransactionController {

    private final BuyRepository buyRepository;
    private final ProcessPayment processPayment;

    public TransactionController(ProcessPayment processPayment, BuyRepository buyRepository, Set<BuyEvent> events) {
        this.buyRepository = buyRepository;
        this.processPayment = processPayment;
    }

    @Transactional
    @PostMapping("/pagseguro/{buyId}")
    public ResponseEntity<?> transaction(@PathVariable Long buyId, @Valid TransactionPagseguroRequest request) {
        return processPayment(buyId, request);
    }

    @Transactional
    @PostMapping("/paypal/{buyId}")
    public ResponseEntity<?> transactionPaypal(@PathVariable Long buyId, @Valid TransactionPaypalRequest request) {
        return processPayment(buyId, request);
    }

    private ResponseEntity<?> processPayment(Long buyId, TransactionRequest request) {
        Optional<Buy> buyOptional = buyRepository.findById(buyId);

        if (buyOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return processPayment.isProcessed(request, buyOptional.get()) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}
