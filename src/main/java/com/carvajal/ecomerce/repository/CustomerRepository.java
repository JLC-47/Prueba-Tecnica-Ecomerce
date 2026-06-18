package com.carvajal.ecomerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carvajal.ecomerce.entity.Customers;

public interface CustomerRepository extends JpaRepository<Customers, Long> {
    
}
