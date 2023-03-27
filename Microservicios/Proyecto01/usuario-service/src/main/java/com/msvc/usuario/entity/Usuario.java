package com.msvc.usuario.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "usuarios")
public class Usuario {

    @Id
    private String id;

    @Column(length = 20)
    private String nombre;

    private String email;

    private String informacion;

    @Transient
    private List<Calificacion> calificaciones = new ArrayList<>();

}
