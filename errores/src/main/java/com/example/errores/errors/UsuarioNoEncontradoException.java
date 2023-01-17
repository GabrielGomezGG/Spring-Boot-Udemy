package com.example.errores.errors;

public class UsuarioNoEncontradoException extends RuntimeException{

    public UsuarioNoEncontradoException(String id){
        super("Usuario con el id: ".concat(id).concat(" no existe en el sistema."));
    }
}
