package com.dtatkison.shoppingcart.Repositories;

import com.dtatkison.shoppingcart.Models.PendingOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PendingOrderItemRepository extends JpaRepository<PendingOrderItem, Integer> {
}
