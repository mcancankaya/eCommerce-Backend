package com.mcancankaya.ecommerce.core.exceptions;

import com.mcancankaya.ecommerce.core.response.CustomResponse;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomResponse<?> handleBusinessException(BusinessException businessException) {
        return new CustomResponse<>(businessException.getMessage(), false);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomResponse<?> handleValidationException(MethodArgumentNotValidException validationException) {
        Map<String, String> errorMaps = new HashMap<>();
        List<FieldError> fieldErrors = validationException.getBindingResult().getFieldErrors();
        fieldErrors.forEach(error -> errorMaps.put(error.getField(), error.getDefaultMessage()));
        return new CustomResponse<>(errorMaps, "Validation Exception", false);
    }

}
