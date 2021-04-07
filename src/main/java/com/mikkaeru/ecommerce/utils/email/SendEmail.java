package com.mikkaeru.ecommerce.utils.email;

import com.mikkaeru.ecommerce.model.buy.Buy;
import com.mikkaeru.ecommerce.model.user.User;

public interface SendEmail {

    void sendEmail(User productOwner);
    void sendEmailSuccess(Buy buy);
    void sendEmailError(Buy buy);
}
