package com.dtatkison.shoppingcart.Models;

import javax.persistence.*;

@Entity
@Table(name = "OrderItem")
public class OrderItem {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "itemId")
    @Id
    private Integer itemId;

    @ManyToOne
    @JoinColumn(name="productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

    private int quantity;

    public OrderItem() { }

    public OrderItem(Product product, int quantity)
    {
        this.product = product;
        this.quantity = quantity;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
