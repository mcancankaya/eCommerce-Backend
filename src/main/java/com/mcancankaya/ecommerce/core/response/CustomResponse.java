package com.mcancankaya.ecommerce.core.response;

import lombok.Data;

@Data
public class CustomResponse<T> {
    private Boolean status = true;
    private T data;
    private String message;

    public CustomResponse(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public CustomResponse(T data, String message, boolean status) {
        this(data, message);
        this.status = status;
    }

    public CustomResponse(String message) {
        this(null, message);
    }

    public CustomResponse(String message, Boolean status) {
        this(message);
        this.status = status;
    }
}
