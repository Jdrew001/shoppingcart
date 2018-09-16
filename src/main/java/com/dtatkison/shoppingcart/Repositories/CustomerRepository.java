package com.dtatkison.shoppingcart.Repositories;

import com.dtatkison.shoppingcart.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
