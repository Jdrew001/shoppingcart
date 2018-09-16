package com.dtatkison.shoppingcart.Services;

import com.dtatkison.shoppingcart.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
}
