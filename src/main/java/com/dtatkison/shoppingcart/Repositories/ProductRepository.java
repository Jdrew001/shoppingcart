package com.dtatkison.shoppingcart.Repositories;

import com.dtatkison.shoppingcart.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
