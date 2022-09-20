package com.example.demoform.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private String id;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String apellido;

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String email;
}
