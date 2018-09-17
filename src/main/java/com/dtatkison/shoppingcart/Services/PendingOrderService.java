package com.dtatkison.shoppingcart.Services;

import com.dtatkison.shoppingcart.Models.PendingOrder;
import com.dtatkison.shoppingcart.Repositories.PendingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PendingOrderService {

    @Autowired
    private PendingOrderRepository pendingOrderRepository;

    //add
    public boolean addNewPendingOrder(PendingOrder order)
    {
        try {
            PendingOrder ord = new PendingOrder(order);
            this.pendingOrderRepository.save(ord);
        } catch(Exception ex) {
            return false;
        }

        return true;
    }

    //get one
    public PendingOrder getPendingOrderById(Integer id)
    {
        Optional<PendingOrder> order = this.pendingOrderRepository.findById(id);
        order.orElseThrow(() -> new RuntimeException("Order not found"));
        return order.get();
    }

    //get all
    public List<PendingOrder> getAllPendingOrders()
    {
        List<PendingOrder> pendingOrders = this.pendingOrderRepository.findAll();
        return pendingOrders;
    }

    //delete
    public boolean deletePendingOrder(Integer id)
    {
        Optional<PendingOrder> tempCust = this.pendingOrderRepository.findById(id);
        tempCust.orElseThrow(() -> new RuntimeException("Customer not found"));
        this.pendingOrderRepository.deleteById(id);

        return true;
    }
}
