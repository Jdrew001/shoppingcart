package com.dtatkison.shoppingcart.Services;

import com.dtatkison.shoppingcart.Models.Address;
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
        //add a new address, orderItem and order
        //you need to make sure that you add an order to an address
        //you need to make sure that you add an order to an orderItem

        try {
            Order ord = new Order(order);
            this.orderRepository.save(ord);
            for (Address a : ord.getAddresses()) {
                Address address = new Address(a);
                address.setOrder(ord);
                this.addressRepository.save(address);
            }
            for (OrderItem i : ord.getOrderItems())
            {
                OrderItem orderItem = new OrderItem(i);
                orderItem.setOrder(ord);
                this.orderItemRepository.save(orderItem);
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
