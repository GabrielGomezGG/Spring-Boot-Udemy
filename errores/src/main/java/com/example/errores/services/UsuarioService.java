package com.example.errores.services;

import com.example.errores.models.domain.Usuario;

import java.util.List;

public interface UsuarioService {

    public List<Usuario> getAllUsuario();
    public Usuario getUsuarioForId(Integer id);

}
