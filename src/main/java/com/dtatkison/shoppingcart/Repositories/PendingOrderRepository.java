package com.dtatkison.shoppingcart.Repositories;

import com.dtatkison.shoppingcart.Models.PendingOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PendingOrderRepository extends JpaRepository<PendingOrder, Integer> {

    public List<PendingOrder> getAllByCustomerIpAddress(String ip);
    public PendingOrder getPendingOrderByCustomerIpAddress(String ip);

}
