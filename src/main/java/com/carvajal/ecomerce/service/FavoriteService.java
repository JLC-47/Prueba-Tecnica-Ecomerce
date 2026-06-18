package com.carvajal.ecomerce.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.carvajal.ecomerce.dto.FavoriteRequestDTO;
import com.carvajal.ecomerce.dto.FavoriteResponseDTO;
import com.carvajal.ecomerce.dto.HttpGlobalResposeDTO;
import com.carvajal.ecomerce.dto.MessageResponseDTO;
import com.carvajal.ecomerce.entity.Customers;
import com.carvajal.ecomerce.entity.FHistory;
import com.carvajal.ecomerce.entity.Favorites;
import com.carvajal.ecomerce.entity.Products;
import com.carvajal.ecomerce.repository.CustomerRepository;
import com.carvajal.ecomerce.repository.FHistoryRepository;
import com.carvajal.ecomerce.repository.FavoriteRepository;
import com.carvajal.ecomerce.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    
    private final FavoriteRepository favoriteRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final FHistoryRepository fHistoryRepository;


    public MessageResponseDTO addFavorite(FavoriteRequestDTO request){
        MessageResponseDTO message = new MessageResponseDTO();

        Optional<Customers> customerFound = customerRepository.findById(request.getIdCustomer());
        Optional<Products> productFound = productRepository.findById(request.getIdProduct());
    
        if (customerFound.isEmpty()) {
            message.setMessage("Error No se encontro el id del cliente");
            return message;
        }

        if (productFound.isEmpty()) {
            message.setMessage("Error, no se encuenta el id del producto");
            return message;
        }

        Favorites favorite = new Favorites();
        favorite.setCustomer(customerFound.get());
        favorite.setProduct(productFound.get());
        favorite.setDate(java.time.LocalDateTime.now());
        favoriteRepository.save(favorite);

        
        FHistory history = new FHistory();
        history.setCustomer(customerFound.get());
        history.setProduct(productFound.get());
        history.setAction("Agregado");
        history.setActionDate(LocalDateTime.now());
        fHistoryRepository.save(history);

        
        message.setMessage("Producto agregado a la lista de favoritos");
        return message;
    }


    public HttpGlobalResposeDTO<List<FavoriteResponseDTO>> getFavoriteId(Long id){

        HttpGlobalResposeDTO<List<FavoriteResponseDTO>> response = new HttpGlobalResposeDTO<>();

        Optional<Customers> customerFound = customerRepository.findById(id);
        
        if (customerFound.isEmpty()) {
            response.setMessage("Error: El cliente con ID " + id + " no existe.");
            response.setData(null);
            return response;
        }
        
        List<Favorites> favorites = favoriteRepository.findByCustomerId(id);

        List<FavoriteResponseDTO> dtos = new ArrayList<>();

        for (Favorites fav : favorites) {
            FavoriteResponseDTO dto = new FavoriteResponseDTO();

            dto.setId(fav.getId());
            dto.setDate(fav.getDate());
            dto.setIdProduct(fav.getProduct().getId());
            dto.setProductName(fav.getProduct().getName());
            dto.setPrice(fav.getProduct().getPrice());
            dto.setStock(fav.getProduct().getStock()); 
            dto.setBrandName(fav.getProduct().getBrand().getName());
            
            if (fav.getProduct().getStock() == 0) {
                dto.setStockAlertMessage("atención: este producto ya no se encuentra en stock");
            }else{
                dto.setStockAlertMessage(":-)");
            }

            dtos.add(dto);
        }

        response.setMessage("Lista de favoritos obtenida correctamente");
        response.setData(dtos);
        
        return response;

    }


    public MessageResponseDTO deleteFavorite(Long id){

        MessageResponseDTO response = new MessageResponseDTO();

        Optional<Favorites> favoriteFound = favoriteRepository.findById(id);

        if (favoriteFound.isEmpty()) {
            response.setMessage("Registro no encontrado");
            return response;
        }

        Favorites fav = favoriteFound.get();

        FHistory history = new FHistory();

        history.setCustomer(fav.getCustomer());
        history.setProduct(fav.getProduct());
        history.setAction("Eliminado");

        fHistoryRepository.save(history);

        favoriteRepository.delete(fav);

        response.setMessage("Producto eliminado de favoritos correctamente");
        return response;

    }
}
