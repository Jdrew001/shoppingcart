package com.dtatkison.shoppingcart.Repositories;

import com.dtatkison.shoppingcart.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
