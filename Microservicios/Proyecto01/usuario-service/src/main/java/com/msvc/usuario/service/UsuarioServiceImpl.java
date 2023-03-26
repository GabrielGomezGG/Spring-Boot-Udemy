package com.msvc.usuario.service;

import com.msvc.usuario.entity.Calificacion;
import com.msvc.usuario.entity.Hotel;
import com.msvc.usuario.entity.Usuario;
import com.msvc.usuario.exceptions.ResourceNotFountException;
import com.msvc.usuario.external.services.CalificacionService;
import com.msvc.usuario.external.services.HotelService;
import com.msvc.usuario.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UsuarioServiceImpl  implements UsuarioService{

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CalificacionService calificacionService;

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        var usuarioID = UUID.randomUUID().toString();
        usuario.setId(usuarioID);
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> getAllUsuarios() {

        var usuarios = usuarioRepository.findAll();

        return usuarios
                .stream()
                .map(it -> {
                    var calificaciones = calificacionService.getCalificacionesPorUsuarioId(it.getId());
                    it.setCalificaciones(calificaciones);
                    return it;
                }).toList();
    }

    @Override
    public Usuario getUsuario(String usuarioId) {

        var usuario = usuarioRepository
                .findById(usuarioId)
                .orElseThrow( () -> new ResourceNotFountException("Usuario no encontrado con el id: " + usuarioId));


        var calificaciones = calificacionService.getCalificacionesPorUsuarioId(usuario.getId());

        var listaCalificaciones = calificaciones.stream().map(calificacion -> {

            Hotel hotel = hotelService.getHotel(calificacion.getHotelId());

            calificacion.setHotel(hotel);

            return calificacion;
        }).collect(Collectors.toList());

        usuario.setCalificaciones(listaCalificaciones);

        return usuario;


    }
}
