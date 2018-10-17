package com.dtatkison.shoppingcart.Services;

import com.dtatkison.shoppingcart.Models.PendingOrder;
import com.dtatkison.shoppingcart.Models.PendingOrderItem;
import com.dtatkison.shoppingcart.Models.Product;
import com.dtatkison.shoppingcart.Repositories.PendingOrderItemRepository;
import com.dtatkison.shoppingcart.Repositories.PendingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PendingOrderService {

    @Autowired
    private PendingOrderRepository pendingOrderRepository;

    @Autowired
    private PendingOrderItemRepository pendingOrderItemRepository;

    //add
    public boolean addNewPendingOrder(PendingOrderItem item, String ip)
    {
        try {
            PendingOrder ord = new PendingOrder();
            ord.setCustomerIpAddress(ip);
            ord.getPendingOrderItems().add(item);
            item.setPendingOrder(ord);
            this.pendingOrderRepository.save(ord);
            this.pendingOrderItemRepository.save(item);
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

    //get all by ipaddress
    public List<PendingOrderItem> getAllByCustomerIpAddress(String ipAddress)
    {
        PendingOrder pendingOrders = this.pendingOrderRepository.getPendingOrderByCustomerIpAddress(ipAddress);
        if(pendingOrders == null)
            return new ArrayList<>();
        else
            return pendingOrders.getPendingOrderItems();
    }

    public PendingOrder getPendingOrderByIpAddress(String ipAddress)
    {
        PendingOrder pendingOrder = this.pendingOrderRepository.getPendingOrderByCustomerIpAddress(ipAddress);
        if(pendingOrder == null)
            return null;
        else
            return pendingOrder;
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

    //add pending order
    public boolean addNewProduct(PendingOrder order, PendingOrderItem item)
    {
        this.pendingOrderItemRepository.save(item);
        this.pendingOrderRepository.save(order);
        return true;
    }

    //update pending order items
    public boolean updateOrderItem(int id, int quantity)
    {
        try {
            this.pendingOrderItemRepository.updateQuantity(quantity, id);
        } catch(Exception ex) {
            return false;
        } finally {
            return true;
        }
    }
}
