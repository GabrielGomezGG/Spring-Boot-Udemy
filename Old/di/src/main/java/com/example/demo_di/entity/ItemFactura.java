package com.example.demo_di.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemFactura {

    private Producto producto;
    private Integer cantidad;

    public Integer calcularImporte(){
        return producto.getPrecio() * cantidad;
    }
}
