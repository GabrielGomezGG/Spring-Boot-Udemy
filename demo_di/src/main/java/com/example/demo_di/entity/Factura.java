package com.example.demo_di.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Getter
@Setter
@Component
public class Factura {

    @Value("${factura.descripcion}")
    private String descripcion;

    @Autowired
    private Cliente cliente;

    @PostConstruct
    public void cambiarDatos(){
        cliente.setNombre("Gogo");
        descripcion = descripcion + "LOLOLOLO";
    }

    @PreDestroy
    public void destruir(){
        System.out.println("DESTROID " + descripcion);
    }

    @Autowired
    @Qualifier("ItemFacturaOficina")
    private List<ItemFactura> items;
}
