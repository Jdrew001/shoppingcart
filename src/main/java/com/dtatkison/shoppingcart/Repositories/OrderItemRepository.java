package com.dtatkison.shoppingcart.Repositories;

import com.dtatkison.shoppingcart.Models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
