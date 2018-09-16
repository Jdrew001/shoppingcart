package com.dtatkison.shoppingcart.Services;

import com.dtatkison.shoppingcart.Repositories.PendingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PendingOrderService {

    @Autowired
    private PendingOrderRepository pendingOrderRepository;
}
