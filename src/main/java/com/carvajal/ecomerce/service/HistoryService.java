package com.carvajal.ecomerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.carvajal.ecomerce.dto.HistoryResponseDTO;
import com.carvajal.ecomerce.dto.HttpGlobalResposeDTO;
import com.carvajal.ecomerce.entity.Customers;
import com.carvajal.ecomerce.entity.FHistory;
import com.carvajal.ecomerce.repository.CustomerRepository;
import com.carvajal.ecomerce.repository.FHistoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HistoryService {
    
    private final FHistoryRepository fHistoryRepository;

    private final CustomerRepository customerRepository;


    public HttpGlobalResposeDTO<List<HistoryResponseDTO>> getHistoryCustomer(Long id){

        HttpGlobalResposeDTO<List<HistoryResponseDTO>> response = new HttpGlobalResposeDTO<>();

        Optional<Customers> customerFound = customerRepository.findById(id);

        if (customerFound.isEmpty()) {
            response.setMessage("Error: El cliente con ID " + id + " no existe.");
            response.setData(null);
            return response;
        }

        List<FHistory> histories = fHistoryRepository.findByCustomerId(id);

        List<HistoryResponseDTO> dtos = new ArrayList<>();

        for (FHistory history : histories) {
            HistoryResponseDTO dto = new HistoryResponseDTO();

            dto.setId(history.getId());
            dto.setCustomerName(history.getCustomer().getName());
            dto.setProductName(history.getProduct().getName());
            dto.setAction(history.getAction());
            dto.setActionDate(history.getActionDate());

            dtos.add(dto);
        }

        response.setMessage("Historial de auditoría del cliente obtenido con éxito");
        response.setData(dtos);
        
        return response;
    }
}
