package com.dtatkison.shoppingcart.Models;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
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

    private Product() {}

    public Product(String productName, double productPrice, String productDescription, byte[] imageData)
    {
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

    //convert byte to image
    public String convertByteToImage() throws IOException
    {
        return new String(Base64.encodeBase64(imageData));
    }
}
