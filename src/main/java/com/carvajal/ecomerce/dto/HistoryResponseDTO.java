package com.carvajal.ecomerce.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class HistoryResponseDTO {
    
    private Long id;
    private String customerName;
    private String productName;
    private String action;
    private LocalDateTime actionDate;
}
