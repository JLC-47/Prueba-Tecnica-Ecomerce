package com.carvajal.ecomerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carvajal.ecomerce.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    
}
