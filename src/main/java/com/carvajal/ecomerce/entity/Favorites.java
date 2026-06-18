package com.carvajal.ecomerce.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "favorites")
public class Favorites {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false) 
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "id_customer", nullable = false)
    private Customers customer;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Products product;
}
