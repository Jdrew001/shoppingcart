package com.dtatkison.shoppingcart.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Product")
public class Product {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "productId")
    @Id
    private Integer productId;

    @Column(name = "productName")
    private String productName;

    @Column(name = "description")
    private String productDescription;

    @Column(name = "productPrice")
    private double productPrice;

    //relationships
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "session",
            joinColumns = {@JoinColumn(name = "productId")},
            inverseJoinColumns = {@JoinColumn(name = "pendingOrderId")})
    private List<PendingOrder> pendingOrders = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "productorders",
            joinColumns = {@JoinColumn(name = "productId")},
            inverseJoinColumns = {@JoinColumn(name = "orderId")})
    private List<Order> orders = new ArrayList<>();

    public Product() {}

    public Product(String productName, double productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public Product(Product product)
    {
        this.productName = product.productName;
        this.productPrice = product.productPrice;
        this.productDescription = product.productDescription;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
}
