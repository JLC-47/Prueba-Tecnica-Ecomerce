package com.carvajal.ecomerce.controller;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carvajal.ecomerce.dto.FavoriteRequestDTO;
import com.carvajal.ecomerce.dto.FavoriteResponseDTO;
import com.carvajal.ecomerce.dto.HttpGlobalResposeDTO;
import com.carvajal.ecomerce.dto.MessageResponseDTO;
import com.carvajal.ecomerce.service.FavoriteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping("/add-favorites")
    public ResponseEntity<MessageResponseDTO> addFavorite(@RequestBody FavoriteRequestDTO request){
        MessageResponseDTO response = favoriteService.addFavorite(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-favorite-customer/{id}")
    public ResponseEntity<HttpGlobalResposeDTO<List<FavoriteResponseDTO>>> getFavoriteId(@PathVariable Long id){
        HttpGlobalResposeDTO<List<FavoriteResponseDTO>> response = favoriteService.getFavoriteId(id);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/delete-favorite/{id}")
    public ResponseEntity<MessageResponseDTO> deleteFavorite(@PathVariable Long id){
        MessageResponseDTO response = favoriteService.deleteFavorite(id);
        return ResponseEntity.ok(response);
    }
}
