package com.mikkaeru.ecommerce.service;

import com.mikkaeru.ecommerce.listener.BuyEvent;
import com.mikkaeru.ecommerce.model.buy.Buy;
import com.mikkaeru.ecommerce.model.buy.TransactionRequest;
import com.mikkaeru.ecommerce.repository.buy.BuyRepository;
import com.mikkaeru.ecommerce.service.impl.EmailServiceFakeImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProcessPayment {

    private final Set<BuyEvent> events;
    private final EmailService emailService;
    private final BuyRepository buyRepository;

    public ProcessPayment(BuyRepository buyRepository, Set<BuyEvent> events) {
        this.events = events;
        this.buyRepository = buyRepository;
        this.emailService = new EmailServiceFakeImpl();
    }

    public ResponseEntity<?> process(TransactionRequest request, Buy buy) {
        boolean isProcessed = buy.paymentAttempt(request);

        if (isProcessed) {
            buyRepository.save(buy);

            events.forEach(events -> events.processes(buy));

            emailService.sendEmailSuccess(buy);

            return ResponseEntity.ok().build();
        }

        emailService.sendEmailError(buy);
        return ResponseEntity.badRequest().build();
    }
}
