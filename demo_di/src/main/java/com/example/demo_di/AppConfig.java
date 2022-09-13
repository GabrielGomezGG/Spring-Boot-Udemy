package com.example.demo_di;

import com.example.demo_di.entity.ItemFactura;
import com.example.demo_di.entity.Producto;
import com.example.demo_di.models.services.IService;
import com.example.demo_di.models.services.MiServicio;
import com.example.demo_di.models.services.MiServicioComplejo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean("MiServicioPrincipal")
    public IService registrarServicioSimple(){
        return new MiServicio();
    }

    @Bean("MiServicioComplejo")
    public IService registrarServicioComplejo(){
        return new MiServicioComplejo();
    }

    @Bean("ItemFactura")
    public List<ItemFactura> registrarItems(){

        Producto producto1 = new Producto("Tomate", 100);
        Producto producto2 = new Producto("Tomato", 2200);
        Producto producto3 = new Producto("Tomatu", 300);

        ItemFactura item1 = new ItemFactura(producto1, 2);
        ItemFactura item2 = new ItemFactura(producto2, 4);
        ItemFactura item3 = new ItemFactura(producto3, 6);

        return Arrays.asList(item1,item2,item3);
    }

    @Bean("ItemFacturaOficina")
    public List<ItemFactura> registrarItemsOficina(){

        Producto producto1 = new Producto("Otra", 100);
        Producto producto2 = new Producto("Cosa", 2200);
        Producto producto3 = new Producto("Lulu", 300);

        ItemFactura item1 = new ItemFactura(producto1, 2);
        ItemFactura item2 = new ItemFactura(producto2, 4);
        ItemFactura item3 = new ItemFactura(producto3, 6);

        return Arrays.asList(item1,item2,item3);
    }
}
