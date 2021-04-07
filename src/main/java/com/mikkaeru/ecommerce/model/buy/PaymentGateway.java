package com.mikkaeru.ecommerce.model.buy;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public enum PaymentGateway {
    pagseguro{
        @Override
        public String generateUrl(UriComponentsBuilder builder, Buy newBuy) {
            url = builder.path("/pagseguro/{code}").buildAndExpand(newBuy.getCode().toString());
            return "pagseguro.com?returnId="+newBuy.getId()+"&redirectUrl="+url;
        }
    },
    paypal {
        @Override
        public String generateUrl(UriComponentsBuilder builder, Buy newBuy) {
            url = builder.path("/paypal/{code}").buildAndExpand(newBuy.getCode().toString());
            return "paypal.com?buyerId="+newBuy.getId()+"&redirectUrl="+url;
        }
    };

    UriComponents url;

    public abstract String generateUrl(UriComponentsBuilder builder, Buy newBuy);
}
