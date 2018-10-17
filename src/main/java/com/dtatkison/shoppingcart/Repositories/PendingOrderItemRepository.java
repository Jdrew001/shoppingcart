package com.dtatkison.shoppingcart.Repositories;

import com.dtatkison.shoppingcart.Models.PendingOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface PendingOrderItemRepository extends JpaRepository<PendingOrderItem, Integer> {

    @Modifying(clearAutomatically = true)
    @Transactional()
    @Query("UPDATE PendingOrderItem p SET p.quantity = :quantity where p.id = :itemId")
    int updateQuantity(@Param("quantity") int quantity, @Param("itemId") Integer id);
}
