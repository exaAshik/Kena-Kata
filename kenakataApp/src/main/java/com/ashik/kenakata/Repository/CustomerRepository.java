package com.ashik.kenakata.Repository;

import com.ashik.kenakata.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
