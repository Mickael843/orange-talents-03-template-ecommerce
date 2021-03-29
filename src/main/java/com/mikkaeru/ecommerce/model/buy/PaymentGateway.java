package com.mikkaeru.ecommerce.model.buy;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public enum PaymentGateway {
    pagseguro, paypal;

    public String generateUrl(UriComponentsBuilder builder, Buy newBuy) {
        UriComponents url;

        if (this.equals(pagseguro)) {
            url = builder.path("/pagseguro/{code}").buildAndExpand(newBuy.getCode().toString());
            return "pagseguro.com?returnId="+newBuy.getId()+"&redirectUrl="+url;
        } else {
            url = builder.path("/paypal/{code}").buildAndExpand(newBuy.getCode().toString());
            return "paypal.com?buyerId="+newBuy.getId()+"&redirectUrl="+url;
        }
    }
}
