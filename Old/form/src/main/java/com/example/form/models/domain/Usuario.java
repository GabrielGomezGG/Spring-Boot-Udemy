package com.example.form.models.domain;

import com.example.form.validation.IdentificadorRegex;
import com.example.form.validation.Requerido;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Usuario {

    //@Pattern(regexp = "[0-9]{2}[.][0-9]{3}[.][0-9]{3}[-][A-Z]")
    @IdentificadorRegex
    private String id;

    //@NotEmpty(message = "nombre incorrecto")
    private String nombre;

    @Requerido
    private String apellido;

    @NotEmpty
    private String username;

    @NotBlank
    @Size(min = 3, max = 7)
    private String password;

    @NotEmpty
    @Email
    private String email;

    @NotNull
    @Min(5)
    @Max(5000)
    private Integer cuenta;

    @NotNull
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;

    @NotNull
    private Pais pais;

    @NotEmpty
    private List<Role> roles;

    private Boolean habilitar;

    @NotEmpty
    private String genero;

    private String valorSecreto;

    @Override
    public String toString() {
        return this.id.toString();
    }
}
