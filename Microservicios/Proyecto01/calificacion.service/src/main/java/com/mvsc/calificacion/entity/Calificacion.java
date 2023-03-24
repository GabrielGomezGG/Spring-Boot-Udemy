package com.mvsc.calificacion.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("calificaciones")
public class Calificacion {

    @Id
    private String id;
    private String usuarioId;
    private String hotelId;
    private Integer calificacion;
    private String observaciones;

}
