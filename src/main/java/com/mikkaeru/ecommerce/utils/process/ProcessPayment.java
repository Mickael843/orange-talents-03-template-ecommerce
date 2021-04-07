package com.mikkaeru.ecommerce.utils.process;

import com.mikkaeru.ecommerce.listener.BuyEvent;
import com.mikkaeru.ecommerce.model.buy.Buy;
import com.mikkaeru.ecommerce.dto.in.buy.TransactionRequest;
import com.mikkaeru.ecommerce.repository.buy.BuyRepository;
import com.mikkaeru.ecommerce.fake.SendEmailFake;
import com.mikkaeru.ecommerce.utils.email.SendEmail;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProcessPayment {

    private final Set<BuyEvent> events;
    private final SendEmail sendEmail;
    private final BuyRepository buyRepository;

    public ProcessPayment(BuyRepository buyRepository, Set<BuyEvent> events) {
        this.events = events;
        this.buyRepository = buyRepository;
        this.sendEmail = new SendEmailFake();
    }

    public boolean isProcessed(TransactionRequest request, Buy buy) {
        boolean isProcessed = buy.paymentAttempt(request);

        if (isProcessed) {
            buyRepository.save(buy);

            events.forEach(events -> events.processes(buy));

            sendEmail.sendEmailSuccess(buy);

            return true;
        }

        sendEmail.sendEmailError(buy);
        return false;
    }
}
