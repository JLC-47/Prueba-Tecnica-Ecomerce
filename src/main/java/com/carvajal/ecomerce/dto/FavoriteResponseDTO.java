package com.carvajal.ecomerce.dto;


import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FavoriteResponseDTO {
   
    private Long id;
    private LocalDateTime date;
    private Long idProduct;
    private String productName;
    private Double price;
    private Integer stock;
    private String brandName;
    
    private String stockAlertMessage;
}
