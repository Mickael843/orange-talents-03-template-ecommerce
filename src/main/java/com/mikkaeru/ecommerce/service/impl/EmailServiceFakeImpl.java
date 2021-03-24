package com.mikkaeru.ecommerce.service.impl;

import com.mikkaeru.ecommerce.model.user.User;
import com.mikkaeru.ecommerce.service.EmailService;

public class EmailServiceFakeImpl implements EmailService {

    @Override
    public void sendEmail(User productOwner) {
        System.out.println("ENVIANDO EMAIL PARA -> " + productOwner.getUsername());
    }
}
