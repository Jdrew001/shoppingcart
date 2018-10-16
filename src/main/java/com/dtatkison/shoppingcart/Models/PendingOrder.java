package com.dtatkison.shoppingcart.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PendingOrder")
public class PendingOrder {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pendingOrderId")
    @Id
    private Integer pendingOrderId;

    @Column(name = "customerIpAddress")
    private String customerIpAddress;

    //relationships
    @ManyToMany(mappedBy = "pendingOrders")
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "pendingOrder")
    private List<PendingOrderItem> pendingOrderItems = new ArrayList<>();

    public PendingOrder()
    {

    }

    public PendingOrder(PendingOrder order)
    {
        this.customerIpAddress = order.customerIpAddress;
    }

    public Integer getPendingOrderId() {
        return pendingOrderId;
    }

    public void setPendingOrderId(Integer pendingOrderId) {
        this.pendingOrderId = pendingOrderId;
    }

    public String getCustomerIpAddress() {
        return customerIpAddress;
    }

    public void setCustomerIpAddress(String customerIpAddress) {
        this.customerIpAddress = customerIpAddress;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public List<PendingOrderItem> getPendingOrderItems() {
        return pendingOrderItems;
    }

    public void setPendingOrderItems(List<PendingOrderItem> pendingOrderItems) {
        this.pendingOrderItems = pendingOrderItems;
    }
}
