package com.carvajal.ecomerce.dto;

import lombok.Data;

@Data
public class FavoriteRequestDTO {
    
    private Long idCustomer;

    private Long idProduct;
}
