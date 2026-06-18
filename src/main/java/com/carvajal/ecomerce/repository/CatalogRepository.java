package com.carvajal.ecomerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carvajal.ecomerce.entity.Catalog;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {
    
}
