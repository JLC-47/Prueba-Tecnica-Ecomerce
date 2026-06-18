package com.carvajal.ecomerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;


import com.carvajal.ecomerce.dto.ProductDTO;
import com.carvajal.ecomerce.entity.Products;
import com.carvajal.ecomerce.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;

    public List<ProductDTO> getCatalog(){

        List<ProductDTO> listProduct = new ArrayList<>();
        List<Products> product = productRepository.findAll();

        for (Products products : product) {
            ProductDTO dto = new ProductDTO();
            
            dto.setId(products.getId());
            dto.setName(products.getName());
            dto.setDescription(products.getDescription());
            dto.setPrice(products.getPrice());
            dto.setStock(products.getStock());
            dto.setBrandName(products.getBrand().getName());
            dto.setCatalogName(products.getCatalog().getName());

            listProduct.add(dto);

        }
        
        return listProduct;
    }
}
