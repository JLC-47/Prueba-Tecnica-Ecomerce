package com.carvajal.ecomerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carvajal.ecomerce.entity.Products;

public interface ProductRepository extends JpaRepository<Products, Long> {
    
}
