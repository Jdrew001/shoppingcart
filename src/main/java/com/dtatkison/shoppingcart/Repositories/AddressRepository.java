package com.dtatkison.shoppingcart.Repositories;

import com.dtatkison.shoppingcart.Models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
