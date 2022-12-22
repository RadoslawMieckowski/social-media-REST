package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotExsitsException extends RuntimeException {

    public UserNotExsitsException(String message) {
        super(message);
    }
}
