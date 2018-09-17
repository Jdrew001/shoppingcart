package com.dtatkison.shoppingcart.Repositories;

import com.dtatkison.shoppingcart.Models.PendingOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PendingOrderRepository extends JpaRepository<PendingOrder, Integer> {



}
