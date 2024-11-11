package com.ashik.kenakata.Service.User;

import com.ashik.kenakata.Entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer saveCustomer(Customer customer);

    Customer findCustomerById(Long id);

    List<Customer> findAllCustomers();

    Customer updateCustomer(Long id, Customer customer);

    void deleteCustomer(Long id);
}
