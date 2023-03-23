package com.msvc.usuario.exceptions.controller;

import com.msvc.usuario.exceptions.ResourceNotFountException;
import com.msvc.usuario.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(ResourceNotFountException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFountException resourceNotFountException){
        var mensaje = resourceNotFountException.getMessage();

        var response = ApiResponse.builder()
                .message(mensaje)
                .success(true)
                .response(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
