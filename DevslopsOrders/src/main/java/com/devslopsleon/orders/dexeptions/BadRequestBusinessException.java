package com.devslopsleon.orders.dexeptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestBusinessException extends RuntimeException {
    public BadRequestBusinessException(String message) {
        super(message);
    }
}
