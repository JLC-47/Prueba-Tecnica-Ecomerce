package com.carvajal.ecomerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carvajal.ecomerce.entity.Favorites;

public interface FavoriteRepository  extends JpaRepository<Favorites, Long>{
    
    List<Favorites> findByCustomerId(Long idCustomer);
}
