package com.dtatkison.shoppingcart.Models;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.persistence.*;
import java.io.IOException;
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

    @Lob
    @Column(name = "imageName")
    private byte[] imageData;

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

    @OneToMany(mappedBy="product")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(mappedBy="product")
    private List<PendingOrderItem> pendingOrderItems = new ArrayList<>();

    public Product() {}

    public Product(String productName, double productPrice, String productDescription, byte[] imageData)
    {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.imageData = imageData;
    }

    public Product(Integer productId, String productName, double productPrice, String productDescription, byte[] imageData)
    {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.imageData = imageData;
    }

    public Product(Product product)
    {
        this.productName = product.productName;
        this.productPrice = product.productPrice;
        this.productDescription = product.productDescription;
        this.imageData = product.imageData;
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

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public List<PendingOrder> getPendingOrders() {
        return pendingOrders;
    }

    public void setPendingOrders(List<PendingOrder> pendingOrders) {
        this.pendingOrders = pendingOrders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    //convert byte to image
    public String convertByteToImage() throws IOException
    {
        return new String(Base64.encodeBase64(imageData));
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<PendingOrderItem> getPendingOrderItems() {
        return pendingOrderItems;
    }

    public void setPendingOrderItems(List<PendingOrderItem> pendingOrderItems) {
        this.pendingOrderItems = pendingOrderItems;
    }
}
