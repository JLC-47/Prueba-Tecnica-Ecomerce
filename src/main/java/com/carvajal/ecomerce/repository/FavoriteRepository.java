package com.carvajal.ecomerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import com.carvajal.ecomerce.entity.Favorites;

public interface FavoriteRepository  extends JpaRepository<Favorites, Long>{
    
    List<Favorites> findByCustomerId(Long idCustomer);

    @Procedure(procedureName = "pa_favorites")
    List<Favorites> getFavorites(Long id);
}
