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

    @Column(name = "custFname")
    private String custFname;

    @Column(name = "custLname")
    private String custLname;

    @Column(name = "cardNum")
    private String cardNum;

    @Column(name = "nameOnCard")
    private String nameOnCard;

    @Column(name = "securityCode")
    private String securityCode;

    @Column(name = "cardType")
    private String cardType;

    @Column(name = "expDate")
    private String expDate;

    //relationships
    @ManyToMany(mappedBy = "orders")
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy ="order")
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order() {}

    public Order(Date orderDate, String orderStatus, String custFname, String custLname, String cardNum, String cardType, String nameOnCard, String expDate) {
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.custFname = custFname;
        this.custLname = custLname;
        this.cardNum = cardNum;
        this.cardType = cardType;
        this.nameOnCard = nameOnCard;
        this.expDate = expDate;
    }

    public Order(Order order) {
        this.orderDate = order.getOrderDate();
        this.orderStatus = order.getOrderStatus();
        this.custFname = order.getCustFname();
        this.custLname = order.getCustLname();
        this.cardNum = order.getCardNum();
        this.cardType = order.getCardType();
        this.nameOnCard = order.getNameOnCard();
        this.expDate = order.getExpDate();
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

    public String getCustFname() {
        return custFname;
    }

    public void setCustFname(String custFname) {
        this.custFname = custFname;
    }

    public String getCustLname() {
        return custLname;
    }

    public void setCustLname(String custLname) {
        this.custLname = custLname;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
