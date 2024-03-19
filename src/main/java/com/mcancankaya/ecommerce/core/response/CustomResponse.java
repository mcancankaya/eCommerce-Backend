package com.mcancankaya.ecommerce.core.response;

import lombok.Data;

@Data
public class CustomResponse<T> {
    private T data;
    private String message;

    public CustomResponse(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public CustomResponse(String message) {
        this(null, message);
    }
}
