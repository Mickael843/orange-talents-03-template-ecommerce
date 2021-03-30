package com.mikkaeru.ecommerce.service;

import com.mikkaeru.ecommerce.model.buy.Buy;
import com.mikkaeru.ecommerce.model.user.User;

public interface EmailService {

    void sendEmail(User productOwner);
    void sendEmailSuccess(Buy buy);
    void sendEmailError(Buy buy);
}
