package com.dtatkison.shoppingcart.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ShoppingOrder")
public class Order {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderId")
    @Id
    private Integer orderId;

    @Column(name = "orderDate")
    private Date orderDate;

    @Column(name = "orderStatus")
    private String orderStatus;

    //relationships
    @ManyToMany(mappedBy = "orders")
    private List<Product> products = new ArrayList<>();

    public Order() {}

    public Order(Date orderDate, String orderStatus) {
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

    public Order(Order order) {
        this.orderDate = order.getOrderDate();
        this.orderStatus = order.getOrderStatus();
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }


}
