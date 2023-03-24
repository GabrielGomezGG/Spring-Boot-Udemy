package com.mvsc.calificacion.service;

import com.mvsc.calificacion.entity.Calificacion;

import java.util.List;

public interface CalificacionService {

    Calificacion create (Calificacion calificacion);

    List<Calificacion> getCalificaciones();

    List<Calificacion> getCalificacionesByUsuarioId(String usuarioId);

    List<Calificacion> getCalificacionesByHotelId(String hotelId);


}
