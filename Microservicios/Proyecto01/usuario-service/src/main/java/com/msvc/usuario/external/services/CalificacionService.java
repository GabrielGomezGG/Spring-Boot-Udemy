package com.msvc.usuario.external.services;

import com.msvc.usuario.entity.Calificacion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CALIFICACION-SERVICE")
public interface CalificacionService {

    @GetMapping("calificaciones/usuarios/{usuarioId}")
    List<Calificacion> getCalificacionesPorUsuarioId(@PathVariable String usuarioId);
}
