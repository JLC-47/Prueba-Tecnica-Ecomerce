package com.carvajal.ecomerce.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carvajal.ecomerce.dto.HistoryResponseDTO;
import com.carvajal.ecomerce.dto.HttpGlobalResposeDTO;
import com.carvajal.ecomerce.service.HistoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping("/Customer/{id}")
    public ResponseEntity<HttpGlobalResposeDTO<List<HistoryResponseDTO>>> getHistoryCostumer(@PathVariable Long id){
        try {
            
            HttpGlobalResposeDTO<List<HistoryResponseDTO>> response = historyService.getHistoryCustomer(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            HttpGlobalResposeDTO<List<HistoryResponseDTO>> response = new HttpGlobalResposeDTO<>();
            response.setMessage("Error al listar el historial");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
