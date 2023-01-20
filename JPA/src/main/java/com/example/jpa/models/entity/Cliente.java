package com.example.jpa.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nombre;
    @NotEmpty
    private String apellido;
    @NotEmpty
    @Email
    private String email;

    @NotNull
    @Column(name = "creado_en")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date creadoEn;

    private String foto;

//    @PrePersist
//    public void PrePersist(){
//        creadoEn = new Date();
//    }

}
