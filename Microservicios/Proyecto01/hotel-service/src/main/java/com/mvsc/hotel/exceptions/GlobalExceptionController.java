package com.mvsc.hotel.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(ResourceNotFountException.class)
    public ResponseEntity<Map<String,Object>> handlerResourceNotFoundException(ResourceNotFountException resourceNotFountException){
        var map = new HashMap<String,Object>();

        var mensaje = resourceNotFountException.getMessage();

        map.put("message", mensaje);
        map.put("success",true);
        map.put("status",HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }
}
