package com.dtatkison.shoppingcart.Services;

import com.dtatkison.shoppingcart.Models.Product;
import com.dtatkison.shoppingcart.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
            return false;
        }

        return true;
    }

    //get one
    public Product getPendingOrderById(Integer id)
    {
        Optional<Product> product = this.productRepository.findById(id);
        product.orElseThrow(() -> new RuntimeException("Order not found"));
        return product.get();
    }

    //get all
    public List<Product> getAllPendingOrders()
    {
        List<Product> pendingOrders = this.productRepository.findAll();
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
}
