package com.example.form.models.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usuario {

    private String id;

    @NotEmpty(message = "nombre incorrecto")
    private String nombre;

    @NotEmpty
    private String apellido;

    @NotEmpty
    private String username;

    @NotEmpty
    @Size(min = 3, max = 7)
    private String password;

    @NotEmpty
    @Email
    private String email;
}
