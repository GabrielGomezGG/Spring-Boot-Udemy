package com.example.errores.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.ParseException;

@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(ArithmeticException.class)
    public String aritmeticaError(ArithmeticException ex, Model model){
        model.addAttribute("error", "Error de aritmetica");
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return "error/aritmetica";
    }

    @ExceptionHandler(NumberFormatException.class)
    public String parseIntError(Exception ex, Model model){
        model.addAttribute("error", "Error al tratar de parsear un string a un integer");
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return "error/errorgenerico";
    }

}
