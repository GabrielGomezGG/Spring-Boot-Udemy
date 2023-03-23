package com.msvc.usuario.service;

import com.msvc.usuario.entity.Usuario;
import com.msvc.usuario.exceptions.ResourceNotFountException;
import com.msvc.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioServiceImpl  implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        var usuarioID = UUID.randomUUID().toString();
        usuario.setId(usuarioID);
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario getUsuario(String usuarioId) {
        return usuarioRepository
                .findById(usuarioId)
                .orElseThrow( () -> new ResourceNotFountException("Usuario no encontrado con el id: " + usuarioId));
    }
}
