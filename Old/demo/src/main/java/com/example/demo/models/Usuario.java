package com.example.demo.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Usuario {
    private String nombre;
    private String apellido;
    private String email;
}
