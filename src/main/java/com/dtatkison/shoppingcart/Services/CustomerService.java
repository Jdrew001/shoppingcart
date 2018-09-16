package com.dtatkison.shoppingcart.Services;

import com.dtatkison.shoppingcart.Models.Customer;
import com.dtatkison.shoppingcart.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    //add new customer
    public boolean addNewCustomer(Customer customer)
    {
        try {
            Customer cust = new Customer(customer);
            this.customerRepository.save(cust);
        } catch(Exception ex) {
            return false;
        }

        return true;
    }


    //delete customer
    public boolean deleteCustomerById(Integer id)
    {
        Optional<Customer> tempCust = this.customerRepository.findById(id);
        tempCust.orElseThrow(() -> new RuntimeException("Customer not found"));
        this.customerRepository.deleteById(id);

        return true;
    }

    //get all customers
    public List<Customer> getAllCustomers()
    {
        List<Customer> customers = this.customerRepository.findAll();
        return customers;
    }

    //get customer by
    public Customer getCustomerById(Integer id)
    {
        Optional<Customer> customer = this.customerRepository.findById(id);
        customer.orElseThrow(() -> new RuntimeException("Customer not found"));

        return customer.get();
    }
}
