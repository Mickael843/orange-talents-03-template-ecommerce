package com.mikkaeru.ecommerce.listener;

import com.mikkaeru.ecommerce.model.buy.Buy;

public interface BuyEvent {

    void processes(Buy buy);
}
