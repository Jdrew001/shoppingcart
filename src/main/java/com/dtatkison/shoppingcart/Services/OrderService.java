package com.dtatkison.shoppingcart.Services;

import com.dtatkison.shoppingcart.Models.Address;
import com.dtatkison.shoppingcart.Models.Constants;
import com.dtatkison.shoppingcart.Models.Order;
import com.dtatkison.shoppingcart.Models.OrderItem;
import com.dtatkison.shoppingcart.Repositories.AddressRepository;
import com.dtatkison.shoppingcart.Repositories.OrderItemRepository;
import com.dtatkison.shoppingcart.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    //get all
    public List<Order> getAllOrders()
    {
        List<Order> orders = this.orderRepository.findAll();
        return orders;
    }

    //get one
    public Order getOrderById(Integer id)
    {
        Optional<Order> order = this.orderRepository.findById(id);
        order.orElseThrow(() -> new RuntimeException("Order not found"));
        return order.get();
    }

    //add
    public boolean addNewOrder(Order order)
    {
        try {
            this.orderRepository.save(order);
            int count = 0;
            for (Address a : order.getAddresses()) {
                if(a.getState() != null && a.getCity() != null)
                {
                    a.setOrder(order);
                    if(count == 0)
                    {
                        count++;
                        a.setAddressType(Constants.SHIPPING_ADDRESS);
                    } else if(count == 1) {
                        count++;
                        a.setAddressType(Constants.BILLING_ADDRESS);
                    }
                    this.addressRepository.save(a);
                }
            }
            for (OrderItem i : order.getOrderItems())
            {
                i.setOrder(order);
                this.orderItemRepository.save(i);
            }
        } catch(Exception ex) {
            return false;
        }

        return true;
    }

    //update
    public boolean updateOrder(Order order)
    {
        try {

        } catch(Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            return false;
        }

        return true;
    }

    //delete
    public boolean deleteOrderById(Integer id)
    {
        Optional<Order> tempCust = this.orderRepository.findById(id);
        tempCust.orElseThrow(() -> new RuntimeException("Customer not found"));
        this.orderRepository.deleteById(id);

        return true;
    }
}
