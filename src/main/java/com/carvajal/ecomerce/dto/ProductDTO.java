package com.carvajal.ecomerce.dto;



import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String brandName;
    private String catalogName;
}
