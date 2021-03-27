package com.mikkaeru.ecommerce.model.buy;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public enum PaymentGateway {
    pagseguro, paypal;

    public String generateUrl(UriComponentsBuilder builder, Buy newBuy) {
        UriComponents url;

        if (this.equals(pagseguro)) {
            url = builder.path("/pagseguro/{id}").buildAndExpand(newBuy.getId().toString());
            return "pagseguro.com?returnId="+newBuy.getId()+"&redirectUrl="+url;
        } else {
            url = builder.path("/paypal/{id}").buildAndExpand(newBuy.getId().toString());
            return "paypal.com?buyerId="+newBuy.getId()+"&redirectUrl="+url;
        }
    }
}
