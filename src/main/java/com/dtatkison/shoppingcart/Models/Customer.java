package com.dtatkison.shoppingcart.Models;

import javax.persistence.*;

@Entity
@Table(name = "Customer")
public class Customer {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customerId")
    @Id
    private Integer customerId;

    @Column(name = "email")
    private String email;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "billingAddress")
    private String billingAddress;

    @Column(name = "shippingAddress")
    private String shippingAddress;

    @Column(name = "creditCardNumber")
    private String creditCardNumber;

    @Column(name = "cardType")
    private String cardType;

    @Column(name = "nameOnCard")
    private String nameOnCard;

    @Column(name = "securityCode")
    private String securityCode;

    @Column(name = "expirationDate")
    private String expirationDate;

    public Customer() {}

    public Customer(String email, String firstname, String lastname, String billingAddress, String shippingAddress, String creditCardNumber, String cardType, String nameOnCard, String securityCode, String expirationDate) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.creditCardNumber = creditCardNumber;
        this.cardType = cardType;
        this.nameOnCard = nameOnCard;
        this.securityCode = securityCode;
        this.expirationDate = expirationDate;
    }

    public Customer(Customer customer) {
        this.email = customer.getEmail();
        this.firstname = customer.getLastname();
        this.billingAddress = customer.getBillingAddress();
        this.shippingAddress = customer.getShippingAddress();
        this.creditCardNumber = customer.getCreditCardNumber();
        this.cardType = customer.getCardType();
        this.nameOnCard = customer.getNameOnCard();
        this.securityCode = customer.getSecurityCode();
        this.expirationDate = customer.getExpirationDate();
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
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

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
