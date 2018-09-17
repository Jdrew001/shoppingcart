package com.dtatkison.shoppingcart.Services;

import com.dtatkison.shoppingcart.Models.Order;
import com.dtatkison.shoppingcart.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

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
            Order ord = new Order(order);
            this.orderRepository.save(ord);
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
