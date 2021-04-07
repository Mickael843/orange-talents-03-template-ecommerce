package com.mikkaeru.ecommerce.fake;

import com.mikkaeru.ecommerce.model.buy.Buy;
import com.mikkaeru.ecommerce.model.user.User;
import com.mikkaeru.ecommerce.utils.email.SendEmail;

public class SendEmailFake implements SendEmail {

    @Override
    public void sendEmail(User productOwner) {
        System.out.println("ENVIANDO EMAIL PARA -> " + productOwner.getUsername());
    }

    @Override
    public void sendEmailSuccess(Buy buy) {
        System.out.println("COMPRA REALIZADA COM SUCESSO...");
    }

    @Override
    public void sendEmailError(Buy buy) {
        System.out.println("FALHA NA COMPRA...");
    }
}
