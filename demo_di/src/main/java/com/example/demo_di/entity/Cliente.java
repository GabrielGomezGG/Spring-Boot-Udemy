package com.example.demo_di.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Cliente {

    @Value("${cliente.nombre}")
    private String nombre;

    @Value("${cliente.apellido}")
    private String apellido;
}
