package com.carvajal.ecomerce.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.carvajal.ecomerce.dto.ProductDTO;
import com.carvajal.ecomerce.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductService productService;

    @GetMapping("/get-catalog")
    public ResponseEntity<List<ProductDTO>> getCatalog(){
        List<ProductDTO> response = productService.getCatalog();
        return ResponseEntity.ok(response);
    }
}
