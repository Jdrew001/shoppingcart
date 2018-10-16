package com.dtatkison.shoppingcart.Models;

import javax.persistence.*;

@Entity
@Table(name = "PendingOrderItem")
public class PendingOrderItem {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "itemId")
    @Id
    private Integer itemId;

    @ManyToOne
    @JoinColumn(name="productId")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "pendingOrderId")
    private PendingOrder pendingOrder;

    public PendingOrderItem() { }

    public PendingOrderItem(Product product, int quantity)
    {
        this.product = product;
        this.quantity = quantity;
    }

    public Integer getId() {
        return itemId;
    }

    public void setId(Integer id) {
        this.itemId = id;
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

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public PendingOrder getPendingOrder() {
        return pendingOrder;
    }

    public void setPendingOrder(PendingOrder pendingOrder) {
        this.pendingOrder = pendingOrder;
    }
}
