package com.dtatkison.shoppingcart.Models;

import javax.persistence.*;

enum AddressType {
    billing, shipping
}


@Entity
@Table(name = "Address")
public class Address {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "addressId")
    @Id
    private Integer id;

    @Column(name = "streetAddress")
    private String streetAddress;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zipcode")
    private String zipcode;

    @Enumerated(EnumType.STRING)
    @Column(name = "addressType")
    private AddressType addressType;

    public Address() {}

    public Address(Address address)
    {
        this.streetAddress = address.streetAddress;
        this.city = address.city;
        this.state = address.state;
        this.zipcode = address.zipcode;
        this.addressType = address.addressType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }
}
