package com.carvajal.ecomerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carvajal.ecomerce.entity.FHistory;


public interface FHistoryRepository extends JpaRepository<FHistory, Long> {
    List<FHistory> findByCustomerId(Long idCustomer);
}
