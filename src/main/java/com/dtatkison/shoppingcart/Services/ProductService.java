package com.dtatkison.shoppingcart.Services;

import com.dtatkison.shoppingcart.Models.Product;
import com.dtatkison.shoppingcart.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    //add
    public boolean addProduct(Product order)
    {
        try {
            Product ord = new Product(order);
            this.productRepository.save(ord);
        } catch(Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            return false;
        }

        return true;
    }

    //edit
    public boolean editProduct(Product product)
    {
        if(product.getImageData() == null){
            Product p = this.productRepository.findById(product.getProductId()).get();
            product.setImageData(p.getImageData());
        }

        this.productRepository.save(product);
        return false;
    }

    //get one
    public Product getProductById(Integer id)
    {
        Optional<Product> product = this.productRepository.findById(id);
        product.orElseThrow(() -> new RuntimeException("Order not found"));
        return product.get();
    }

    //get all
    public List<Product> getAllProducts()
    {
        List<Product> pendingOrders = new ArrayList<>();
        this.productRepository.findAll().forEach(pendingOrders::add);
        return pendingOrders;
    }

    //delete
    public boolean deleteProductId(Integer id)
    {
        Optional<Product> tempCust = this.productRepository.findById(id);
        tempCust.orElseThrow(() -> new RuntimeException("Customer not found"));
        this.productRepository.deleteById(id);

        return true;
    }

    //image file to byte array
    public byte[] convertImageFile(MultipartFile image) throws IOException
    {
        return image.getBytes();
    }
}
