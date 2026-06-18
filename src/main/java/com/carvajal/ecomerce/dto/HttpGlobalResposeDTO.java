package com.carvajal.ecomerce.dto;

import lombok.Data;

@Data
public class HttpGlobalResposeDTO<T> {
    
    private String message;

    private T data;
}
