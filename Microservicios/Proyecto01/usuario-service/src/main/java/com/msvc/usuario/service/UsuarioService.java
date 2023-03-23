package com.msvc.usuario.service;

import com.msvc.usuario.entity.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UsuarioService {

    Usuario saveUsuario(Usuario usuario);
    List<Usuario> getAllUsuarios();
    Usuario getUsuario(String usuarioId);

}
