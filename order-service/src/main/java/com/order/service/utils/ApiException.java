package com.order.service.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiException extends RuntimeException{

    private HttpStatus httpStatus;
    public ApiException(String message) {
        super(message);
    }
    public ApiException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
