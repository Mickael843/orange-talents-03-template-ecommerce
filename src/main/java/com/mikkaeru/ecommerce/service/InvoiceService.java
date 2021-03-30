package com.mikkaeru.ecommerce.service;

import com.mikkaeru.ecommerce.listener.BuyEvent;
import com.mikkaeru.ecommerce.model.buy.Buy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class InvoiceService implements BuyEvent {

    private static final String URL = "http://localhost:8080/api/fake/invoices";

    public void processes(Buy buy) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("buyId", buy.getId(), "buyerId", buy.getLoggedUser().getId());
        restTemplate.postForEntity(URL, request, String.class);
    }
}
