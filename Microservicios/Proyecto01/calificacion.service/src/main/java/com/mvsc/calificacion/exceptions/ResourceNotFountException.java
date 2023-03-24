package com.mvsc.calificacion.exceptions;

public class ResourceNotFountException extends RuntimeException{

    public ResourceNotFountException() {
        super("Recurso no encontrado en el servidor.");
    }

    public ResourceNotFountException(String message) {
        super(message);
    }
}
